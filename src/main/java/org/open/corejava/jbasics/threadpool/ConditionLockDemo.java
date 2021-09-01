package org.open.corejava.jbasics.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLockDemo {
    private final List<String> cons = new ArrayList<String>();
    private final Lock lock = new ReentrantLock();
    private final Condition conFull = lock.newCondition();
    private final Condition conEmpty = lock.newCondition();
    private int maxSize;

    public static void main(String[] args) {
        final ConnectionPool cp = new ConnectionPool();
        cp.initPool(3);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " is returninng connection...");
                    cp.returnConnection("New Connection");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(cp.getConnection() + " is getting by " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void initPool(int size) {
        maxSize = size;
        for (int i = 0; i < size; i++) {
            cons.add("Connection: " + i);
        }
    }

    public String getConnection() {
        String str;
        lock.lock();
        try {
            while (cons.isEmpty()) {
                try {
                    conEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            str = cons.remove(0);
            conFull.signalAll();
        } finally {
            lock.unlock();
        }
        return str;
    }

    public void returnConnection(String con) {
        lock.lock();
        try {
            while (cons.size() == maxSize) {
                try {
                    conFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cons.add(con);
            conEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void expandPool(int size) {
        int currentSize = cons.size(), totalSize = currentSize + size;
        synchronized (cons) {
            for (int i = currentSize; i < totalSize; i++) {
                cons.add("Connection: " + i);
            }
            cons.notifyAll();
        }
    }
}
