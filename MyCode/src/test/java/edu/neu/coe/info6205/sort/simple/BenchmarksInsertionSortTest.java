package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.LazyLogger;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
/*
Implement a main program (or you could do it via your own unit tests) to actually run the following benchmarks:
measure the running times of this sort, using four different initial array ordering situations: random, ordered, partially-ordered and reverse-ordered.
I suggest that your arrays to be sorted are of type Integer. Use the doubling method for choosing n and test for at least five values of n.
Draw any conclusions from your observations regarding the order of growth.
 */
public class BenchmarksInsertionSortTest {

    final static LazyLogger logger = new LazyLogger(Benchmarks.class);
    public static void writeToCSV(String fileName,String line){
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(fileName),true);
            fw.write(line+"\n");
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void randomTest(){
        int initialN=500;
        writeToCSV("randomSort.csv","N,Time");
        for(int i=0;i<7;i++){
            initialN*=2;
            String description="Random generator";
            Helper<Integer> helper=new BaseHelper<>(description,initialN);
            InsertionSort<Integer> insertionSort= new InsertionSort<Integer>(helper);
            Supplier<Integer[]> supplier = () -> helper.random(Integer.class, r -> r.nextInt());
            Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                    description + " for " + initialN + " Integers",
                    (xs) -> Arrays.copyOf(xs, xs.length),
                    insertionSort::mutatingSort,
                    null
            );
            double average=benchmark.runFromSupplier(supplier,50);
            writeToCSV("CSV/Assignment2/randomSort.csv",initialN+","+average);
            logger.info("Average milionSecond :"+average);
        }
    }

    @Test
    public void orderedTest(){
        int initialN=500;
        writeToCSV("orderedSort.csv","N,Time");
        for(int i=0;i<7;i++){
            initialN*=2;
            String description="ordered generator";
            Helper<Integer> helper=new BaseHelper<>(description);
            InsertionSort<Integer> insertionSort= new InsertionSort<Integer>(helper);

            final int finalInitialN = initialN;
            Supplier<Integer[]> supplier = () -> {
                Integer[] data=new Integer[finalInitialN];
                for(int j=0;j<finalInitialN;j++){
                    data[j]=j;
                }
                return data;
            };
            Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                    description + " for " + initialN + " Integers",
                    (xs) -> Arrays.copyOf(xs, xs.length),
                    insertionSort::mutatingSort,
                    null
            );
            double average=benchmark.runFromSupplier(supplier,50);
            writeToCSV("CSV/Assignment2/orderedSort.csv",initialN+","+average);
            logger.info("Average milionSecond :"+average);
        }
    }
    @Test
    public void particiallyOrderedTest(){
        int initialN=500;
        writeToCSV("particialOrderedSort.csv","N,Time");
        Random random=new Random();
        for(int i=0;i<7;i++){
            initialN*=2;
            String description="ordered generator";
            Helper<Integer> helper=new BaseHelper<>(description);
            InsertionSort<Integer> insertionSort= new InsertionSort<Integer>(helper);

            final int finalInitialN = initialN;
            Supplier<Integer[]> supplier = () -> {
                Integer[] data=new Integer[finalInitialN];
                for(int j=0;j<finalInitialN;j++){
                    data[j]= random.nextInt(finalInitialN);
                }
                int orderCount= (int) (finalInitialN*0.3);
                int startOrdedIndex=random.nextInt(finalInitialN-orderCount);
                for (int j=startOrdedIndex;j<finalInitialN;j++){
                    data[j]=startOrdedIndex;
                }
                return data;
            };
            Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                    description + " for " + initialN + " Integers",
                    (xs) -> Arrays.copyOf(xs, xs.length),
                    insertionSort::mutatingSort,
                    null
            );
            double average=benchmark.runFromSupplier(supplier,50);
            writeToCSV("CSV/Assignment2/particialOrderedSort.csv",initialN+","+average);
            logger.info("Average milionSecond :"+average);
        }
    }
    @Test
    public void reverseOrderedTest(){
        int initialN=500;
        writeToCSV("CSV/Assignment2/reverseSort.csv","N,Time");
        for(int i=0;i<7;i++){
            initialN*=2;
            String description="reverse generator";
            Helper<Integer> helper=new BaseHelper<>(description);
            InsertionSort<Integer> insertionSort= new InsertionSort<Integer>(helper);

            final int finalInitialN = initialN;
            Supplier<Integer[]> supplier = () -> {
                Integer[] data=new Integer[finalInitialN];
                for(int j=0;j<finalInitialN;j++){
                    data[j]=finalInitialN-j;
                }
                return data;
            };
            Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                    description + " for " + initialN + " Integers",
                    (xs) -> Arrays.copyOf(xs, xs.length),
                    insertionSort::mutatingSort,
                    null
            );
            double average=benchmark.runFromSupplier(supplier,50);
            writeToCSV("CSV/Assignment2/reverseSort.csv",initialN+","+average);
            logger.info("Average milionSecond :"+average);
        }
    }

}
