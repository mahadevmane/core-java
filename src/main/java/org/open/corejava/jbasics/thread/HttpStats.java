package org.open.corejava.jbasics.thread;

public class HttpStats {
    private final Object lockHttp = new Object();
    private final Object lockHttps = new Object();

    private int incHttpCntr, incHttpsCntr;

    public static void main(String[] args) throws InterruptedException {

        final HttpStats httpStats = new HttpStats();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                httpStats.incrementHttpCntr();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                httpStats.incrementHttpsCntr();
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                httpStats.incrementHttpCntr();
            }
        };

        Thread t4 = new Thread() {
            @Override
            public void run() {
                httpStats.incrementHttpsCntr();
            }
        };

        t1.start();
        Thread.sleep(200);
        t2.start();
        t3.start();
        t4.start();

    }

    public void incrementHttpCntr() {
        synchronized (lockHttp) {
            incHttpCntr++;
            System.out.println("Http Counter: " + incHttpCntr);
            System.out.println(Thread.currentThread().getName() + " is increment http counter...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void incrementHttpsCntr() {
        synchronized (lockHttps) {
            incHttpsCntr++;
            System.out.println("Https Counter: " + incHttpsCntr);
            System.out.println(Thread.currentThread().getName() + " is increment https counter...");
        }
    }
}
