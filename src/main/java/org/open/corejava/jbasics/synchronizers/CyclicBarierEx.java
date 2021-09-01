package org.open.corejava.jbasics.synchronizers;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CyclicBarierEx {

    public static void main(String[] args) {
        // CyclicBarrier barier = new CyclicBarrier(5);
        CyclicBarrier barier = new CyclicBarrier(5, new Runnable() {

            @Override
            public void run() {
                System.out.println("No any barier action...");
            }
        });
        ThreadFactory factory = Executors.defaultThreadFactory();

        for (int i = 0; i < 10; i++)
            factory.newThread(new WorkerThread(barier)).start();
    }

    private static class WorkerThread implements Runnable {
        private final Random r = new Random();
        private final int flag = 1;
        CyclicBarrier barier;

        public WorkerThread(CyclicBarrier barier) {
            this.barier = barier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(r.nextInt(3000)));
                System.out.println(Thread.currentThread().getName() + " is gathering needed data...");

                if (flag == 1)
                    Thread.currentThread().interrupt();

                barier.await();
                System.out.println(Thread.currentThread().getName() + " is ready to send data to server...");
            } catch (InterruptedException e) {
                System.err.println("Interrupted exception...");
            } catch (BrokenBarrierException e) {
                System.err.println("Barier broken exception...");
            }
        }
    }
}
