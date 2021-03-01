package edu.neu.coe.info6205.union_find;

import java.util.*;

public class UFPairHelper {
    private int unionFindInitSize;
    private Random random;
    private int pairsSize;
    private int seed;
    private boolean setSeed;
    private int count;
    private int conncetParesSize;
    private int findListSize;
    private double coefficent=3;

    public UFPairHelper(int unionFindInitSize) {
        this.unionFindInitSize = unionFindInitSize;
        this.conncetParesSize= (int) (unionFindInitSize*coefficent);
        this.findListSize= (int) (unionFindInitSize*coefficent);
        random=new Random();
    }

    public UFPairHelper(int unionFindInitSize,int conncetParesSize,int findListSize) {
        this.unionFindInitSize = unionFindInitSize;
        this.conncetParesSize=conncetParesSize;
        this.findListSize=findListSize;
        random=new Random();
    }

    public UFPairHelper(int unionFindInitSize,int seed,boolean setSeed) {
        this.unionFindInitSize = unionFindInitSize;
        this.conncetParesSize= (int) (unionFindInitSize*coefficent);
        this.findListSize= (int) (unionFindInitSize*coefficent);
        this.seed=seed;
        this.setSeed=setSeed;
        random=new Random();
    }

    public UFPairHelper(int unionFindInitSize,int conncetParesSize,int findListSize,int seed,boolean setSeed) {
        this.unionFindInitSize = unionFindInitSize;
        this.conncetParesSize=conncetParesSize;
        this.findListSize=findListSize;
        this.seed=seed;
        this.setSeed=setSeed;
        random=new Random();
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public List<Integer> randomFindIntegerList(){
        count++;
        if(setSeed) random.setSeed(seed);
        List<Integer> list=new ArrayList<Integer>();
        for (int i=0;i<findListSize;i++){
            int q=random.nextInt(unionFindInitSize);
            list.add(q);
        }
        return list;
    }

    public List<List<Integer>> randomConnectedIntegePairs(){
        count++;
        if(setSeed) random.setSeed(seed);
        List<List<Integer>> list=new ArrayList<List<Integer>>();
        for (int i=0;i<conncetParesSize;i++){
            List<Integer> pairs=new ArrayList<Integer>();
            int q=random.nextInt(unionFindInitSize);
            int p=random.nextInt(unionFindInitSize);
            pairs.add(q);
            pairs.add(p);
            list.add(pairs);
        }
        return list;
    }

    public List<List<Integer>> randomIntegePairs(){
        count++;
        if(setSeed) random.setSeed(seed);
        List<List<Integer>> list=new ArrayList<List<Integer>>();
        pairsSize= (int) Math.pow(unionFindInitSize,1.135)/3;
        for (int i=0;i<pairsSize;i++){
            List<Integer> pairs=new ArrayList<Integer>();
            int q=random.nextInt(unionFindInitSize);
            int p=random.nextInt(unionFindInitSize);
            pairs.add(q);
            pairs.add(p);
            list.add(pairs);
        }
        return list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUnionFindInitSize() {
        return unionFindInitSize;
    }

    public void setUnionFindInitSize(int unionFindInitSize) {
        this.unionFindInitSize = unionFindInitSize;
    }

    public int getPairsSize() {
        return pairsSize;
    }

    public void setPairsSize(int pairsSize) {
        this.pairsSize = pairsSize;
    }
}
