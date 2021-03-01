/**
 * Original code:
 * Copyright © 2000–2017, Robert Sedgewick and Kevin Wayne.
 * <p>
 * Modifications:
 * Copyright (c) 2017. Phasmid Software
 */
package edu.neu.coe.info6205.union_find;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Height-weighted Quick Union with Path Compression
 */
public class UF_HWQUPC implements UF {
    /**
     * Ensure that site p is connected to site q,
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     */
    public void connect(int p, int q) {
        if (!isConnected(p, q)) union(p, q);
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n               the number of sites
     * @param pathCompression whether to use path compression
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF_HWQUPC(int n, boolean pathCompression) {
        initN=n;
        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        this.pathCompression = pathCompression;
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     * This data structure uses path compression
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF_HWQUPC(int n) {
        this(n, true);
    }

    public void show() {
        for (int i = 0; i < parent.length; i++) {
            System.out.printf("%d: %d, %d\n", i, parent[i], height[i]);
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int components() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);

        int currentNode=p;
        int parent=getParent(currentNode);
        while (parent!=currentNode) {
            if(pathCompression){
                doPathCompression(currentNode);
            }
            currentNode=getParent(currentNode);
            parent=getParent(currentNode);
        }
        return parent;
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        // CONSIDER can we avoid doing find again?
        mergeComponents(find(p), find(q));
        count--;
    }

    @Override
    public int size() {
        return parent.length;
    }

    /**
     * Used only by testing code
     *
     * @param pathCompression true if you want path compression
     */
    public void setPathCompression(boolean pathCompression) {
        this.pathCompression = pathCompression;
    }

    @Override
    public String toString() {
        return "UF_HWQUPC:" + "\n  count: " + count +
                "\n  path compression? " + pathCompression +
                "\n  parents: " + Arrays.toString(parent) +
                "\n  heights: " + Arrays.toString(height);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    private void updateParent(int p, int x) {
        parent[p] = x;
    }

    private void updateHeight(int p, int x) {
        height[p] += height[x];
    }

    /**
     * Used only by testing code
     *
     * @param i the component
     * @return the parent of the component
     */
    private int getParent(int i) {
        return parent[i];
    }

    private  int[] parent;   // parent[i] = parent of i
    private  int[] height;   // height[i] = height of subtree rooted at i
    private int count;  // number of components
    private boolean pathCompression;
    private int initN;

    private void mergeComponents(int i, int j) {
        int iroot=find(i);
        int jroot=find(j);
        if(iroot==jroot) return;
        int iRootHeight=height[iroot];
        int jRootHeight=height[jroot];
        if(iRootHeight>=jRootHeight){
            updateParent(jroot,iroot);
            updateHeight(iroot,jroot);
        }else {
            updateParent(iroot,jroot);
            updateHeight(jroot,iroot);
        }
        count--;

    }

    /**
     * This implements the single-pass path-halving mechanism of path compression
     */
    private void doPathCompression(int i) {
        // TO BE IMPLEMENTED update parent to value of grandparent
        int grandparent=getParent(getParent(i));
        updateParent(i,grandparent);
    }

    public static int count(int n){
        UF_HWQUPC uf_hwqupc=new UF_HWQUPC(n);
        int connectCount=0;
        Random random=new Random();
        while (uf_hwqupc.components()!=1){
            int p=random.nextInt(n);
            int q=random.nextInt(n);
            uf_hwqupc.connect(p,q);
            connectCount++;
        }
        return connectCount;
    }

    public void benchMarkUnionMerge(List<List<Integer>> lists){
        count = initN;
        parent = new int[count];
        height = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        for (List<Integer> list:lists){
            mergeComponents(list.get(0),list.get(1));
        }

    }

    public void benchMarkUnionMergeConnect(List<List<Integer>> lists){
        count = initN;
        parent = new int[count];
        height = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        for (List<Integer> list:lists){
            mergeComponents(list.get(0),list.get(1));
        }
        for (List<Integer> list:lists){
            connect(list.get(0),list.get(1));
        }

    }
    public void benchMarkConnect(List<List<Integer>> lists){

        for (List<Integer> list:lists){
            connected(list.get(0),list.get(1));
        }
    }

    public int getInitN() {
        return initN;
    }

    public void setInitN(int initN) {
        this.initN = initN;
    }

    public static void main(String[] args) {
        String fileName="CSV/Assignment3/n_with_connection.csv";
        writeToCSV(fileName,"N,Connection");
        int n=10;
        int times=20;
        int size=100;
        for(int i=0;i<times;i++){
            long sum =0;
            for(int j=0; j<size; j++){
                sum +=count(n);
            }
            long average=sum/size;
            //System.out.println(sum+ " "+ n);
            System.out.println("Average times of connection are "+average+", n is "+n);
            writeToCSV(fileName,n+","+average);
            n*=2;
        }
    }

    public static void writeToCSV(String fileName,String line){
        FileWriter fw = null;
        try {
            File file=new File(fileName);
            if(!file.exists()) file.createNewFile();
            fw = new FileWriter(file,true);
            fw.write(line+"\n");
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
