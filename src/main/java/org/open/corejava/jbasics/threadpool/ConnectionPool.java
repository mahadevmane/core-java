package org.open.corejava.jbasics.threadpool;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private final List<String> cons = new ArrayList<String>();
    private final Object conFull = new Object();
    private final Object conEmpty = new Object();
    private int maxSize;

    public static void main(String[] args) {
        final ConnectionPool cp = new ConnectionPool();
        cp.initPool(3);

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

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " is returninng connection...");
                    cp.returnConnection("New Connection");
                    try {
                        Thread.sleep(300);
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
        synchronized (conEmpty) {
            while (cons.isEmpty()) {
                try {
                    conEmpty.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            str = cons.remove(0);
            synchronized (conFull) {
                conFull.notifyAll();
            }
            return str;
        }
    }

    public void returnConnection(String con) {
        synchronized (conFull) {
            while (cons.size() == maxSize) {
                try {
                    conFull.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cons.add(con);
            synchronized (conEmpty) {
                conEmpty.notifyAll();
            }
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