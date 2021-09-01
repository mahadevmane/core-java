package org.open.corejava.jbasics.threadpool;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterruptLockDemo {

    public static void main(String[] args) throws InterruptedException {
        final CustomIntegerArr ci = new CustomIntegerArr();

        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(ci.getValue(i) + " value get from " + i);
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                Random r = new Random();
                while (true) {
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ci.setValue(i, r.nextInt(100));
                        System.out.println("Value set at: " + i);
                    }
                }
            }
        };

        t1.start();
        t2.start();
        Thread.sleep(3000);
        t2.interrupt();
    }
}

class CustomIntegerArr {
    private final int[] arr = new int[3];
    Lock lock = new ReentrantLock();

    public void setValue(int pos, int value) {
        try {
            lock.lockInterruptibly();
            arr[pos] = value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getValue(int pos) {
        int value = -1;
        try {
            lock.lockInterruptibly();
            value = arr[pos];
        } catch (InterruptedException e) {
            return -1;
        } finally {
            lock.unlock();
        }
        return value;
    }
}
