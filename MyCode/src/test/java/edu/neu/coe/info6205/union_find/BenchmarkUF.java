package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BenchmarkUF {

    @Test
    public void benchmark() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/randomPaires.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN);
            WQUPC wqupc = new WQUPC(initialN);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnion,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/randomPaires.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmark2() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/alter_randomPaires.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN);
            WQUPC wqupc = new WQUPC(initialN);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnion2,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/alter_randomPaires.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmark3() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/weight_randomPaires.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN);
            UF_HWQUPC wqupc = new UF_HWQUPC(initialN,true);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnionMerge,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/weight_randomPaires.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmarkSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/randomPairesSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,3,true);
            WQUPC wqupc = new WQUPC(initialN);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnion,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/randomPairesSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmark2SetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/alter_randomPairesSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,3,true);
            WQUPC wqupc = new WQUPC(initialN);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnion2,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/alter_randomPairesSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmark2_2SetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/alter_randomPaires22Seed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            WQUPC wqupc = new WQUPC(initialN);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnion3,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/alter_randomPaires22Seed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmark3SetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/weight_randomPairesSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,3,true);
            UF_HWQUPC wqupc = new UF_HWQUPC(initialN,true);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnionMerge,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/weight_randomPairesSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmarkUnionAndConnectSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/randomPairesUCSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            WQUPC wqupc = new WQUPC(initialN);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnion,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/randomPairesUCSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmark2UnionAndConnectSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/alter_randomPairesUCSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            WQUPC wqupc = new WQUPC(initialN);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnion2Connect,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/alter_randomPairesUCSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }
    @Test
    public void benchmark2_2UnionAndConnectSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/alter_randomPaires22UCSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            WQUPC wqupc = new WQUPC(initialN);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnion3Connect,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/alter_randomPaires22UCSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmark3UnionAndConnectSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/weight_randomPairesUCSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            UF_HWQUPC wqupc = new UF_HWQUPC(initialN,true);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomIntegePairs();
            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkUnionMergeConnect,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/weight_randomPairesUCSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }


    @Test
    public void benchmark1ConnectSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/randomPairesConnectSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            WQUPC wqupc = new WQUPC(initialN);
            wqupc.benchMarkUnion(ufPairHelper.randomIntegePairs());

            ufPairHelper.setSeed(5);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomConnectedIntegePairs();

            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkConnect,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/randomPairesConnectSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void benchmark2ConnectSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/alter_randomPairesConnectSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            WQUPC wqupc = new WQUPC(initialN);
            wqupc.benchMarkUnion2(ufPairHelper.randomIntegePairs());

            ufPairHelper.setSeed(5);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomConnectedIntegePairs();

            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkConnect,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/alter_randomPairesConnectSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }


    @Test
    public void benchmark22ConnectSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/alter_randomPaires22ConnectSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            WQUPC wqupc = new WQUPC(initialN);
            wqupc.benchMarkUnion3(ufPairHelper.randomIntegePairs());

            ufPairHelper.setSeed(5);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomConnectedIntegePairs();

            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkConnect,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/alter_randomPaires22ConnectSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }


    @Test
    public void benchmark3ConnectSetSeed() {
        int initialN = 500;
        writeToCSV("CSV/Assignment4/weight_randomPairesConnectSeed1.csv", "M,N,Time");
        int times=10;
        for (int i = 0; i < times; i++) {
            initialN *= 2;
            String description = "Random generator";
            UFPairHelper ufPairHelper = new UFPairHelper(initialN,1,true);
            UF_HWQUPC wqupc = new UF_HWQUPC(initialN,true);
            wqupc.benchMarkUnionMerge(ufPairHelper.randomIntegePairs());

            ufPairHelper.setSeed(5);
            Supplier<List<List<Integer>>> supplier = () -> ufPairHelper.randomConnectedIntegePairs();

            Benchmark<List<List<Integer>>> benchmark = new Benchmark_Timer<List<List<Integer>>>(
                    description + " for " + initialN + " Integers",
                    null,
                    wqupc::benchMarkConnect,
                    null
            );
            double average = benchmark.runFromSupplier(supplier, 100);
            writeToCSV("CSV/Assignment4/weight_randomPairesConnectSeed1.csv", ufPairHelper.getPairsSize()+","+initialN + "," + average);
            System.out.println("Average milionSecond :" + average+", Pairs size :"+ufPairHelper.getPairsSize()+", N size :"+ initialN);
        }
    }

    @Test
    public void test(){
        Random a=new Random();
        for (int i=0;i<50;i++){
            a.setSeed(1);
            System.out.print(a.nextInt(50));
        }
    }

    public static void writeToCSV(String fileName, String line) {
        FileWriter fw = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) file.createNewFile();
            fw = new FileWriter(file, true);
            fw.write(line + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
