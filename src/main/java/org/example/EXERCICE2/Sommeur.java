package org.example.EXERCICE2;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Sommeur implements Runnable {
    private int[] array;
    private int start;
    private int end;
    private int sum;

    public Sommeur(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; 
        }

        int numThreads = 4;
        int chunkSize = array.length / numThreads;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        Sommeur[] sommeurs = new Sommeur[numThreads];
        Future<?>[] futures = new Future[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? array.length : start + chunkSize;
            sommeurs[i] = new Sommeur(array, start, end);
            futures[i] = executor.submit(sommeurs[i]);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        int totalSum = 0;
        for (Sommeur sommeur : sommeurs) {
            totalSum += sommeur.getSum();
        }

        System.out.println("Somme totale: " + totalSum);
    }
}
