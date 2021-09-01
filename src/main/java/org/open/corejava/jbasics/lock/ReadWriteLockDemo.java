package org.open.corejava.jbasics.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        final CustomIntegerArr ci = new CustomIntegerArr();

        Thread t1 = new Thread() {
            public void run() {
                Random r = new Random();
                while (true) {
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ci.setValue(i, r.nextInt(100));
                        System.out.println("Value set at: " + i);
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(ci.getValue(i) + " value get from " + i);
                    }
                }
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                while (true) {
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(ci.getValue(i) + " value get from " + i);
                    }
                }
            }
        };

        t1.start();
        Thread.sleep(1200);
        t2.start();
        t3.start();
    }
}

class CustomIntegerArr {
    private final int[] arr = new int[3];
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void setValue(int pos, int value) {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " is writing...");
            Thread.sleep(1000);
            arr[pos] = value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Writing " + Thread.currentThread().getName() + " completed...");
            writeLock.unlock();
        }
    }

    public int getValue(int pos) {
        int value = -1;
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " is reading...");
            Thread.sleep(1000);
            value = arr[pos];
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Reading by " + Thread.currentThread().getName() + " completed...");
            readLock.unlock();
        }
        return value;
    }
}
