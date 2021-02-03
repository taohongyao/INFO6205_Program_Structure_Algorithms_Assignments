/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        x+=dx;
        y+=dy;
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        for (int i=0;i<m;i++) {
            randomMove();
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        double distance=Math.sqrt(x*x+y*y);
        return distance;
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
//        List<Integer> list5=new ArrayList<Integer>();
//        List<Integer> list2=new ArrayList<Integer>();
        List<Integer> list1=new ArrayList<Integer>();
        for (int i=0;i<500000;i+=50){
            list1.add(i);
        }
        writeToCSV(list1,30);
//
//        for(int i=1;i<10;i++){
//            list5.add((int)Math.pow(5,i));
//
//        }
//        for(int i=1;i<20;i++){
//            list2.add((int)Math.pow(2,i));
//        }

//        System.out.println("--------5 times--------");
//        walkFromList(list5,30);
//        System.out.println("--------2 times--------");
//        walkFromList(list2,30);
//        System.out.println("--------1 times--------");
//        walkFromList(list1,30);
    }
    public static void walkFromList(List<Integer> steps,int testsCount){
        for (int i=0;i<steps.size();i++){
            int n = testsCount;
            int m=steps.get(i);
            double meanDistance = randomWalkMulti(m, n);
            System.out.println(m + "," + meanDistance);
//            System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
        }
    }
    public static void writeToCSV(List<Integer> steps,int testsCount){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("StepsAndDistance.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder header = new StringBuilder();
        String columnNamesList = "steps,distance";
        // No need give the headers Like: id, Name on builder.append
        header.append(columnNamesList +"\n");
        pw.write(header.toString());

        for (int i=0;i<steps.size();i++){
            int n = testsCount;
            int m=steps.get(i);
            double meanDistance = randomWalkMulti(m, n);
//            System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
            StringBuilder builder = new StringBuilder();
            builder.append(m+",");
            builder.append(meanDistance);
            builder.append('\n');
            if(i%100==0) System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
            pw.write(builder.toString());
        }
        pw.close();
    }
}
