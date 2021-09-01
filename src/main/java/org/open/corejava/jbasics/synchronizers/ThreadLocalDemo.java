package org.open.corejava.jbasics.synchronizers;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadLocalDemo {

    private static final ThreadLocal<String> conn = new ThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory executor = Executors.defaultThreadFactory();
        Thread t1 = executor.newThread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Connection 1 setting value...");
                conn.set("Connection 1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(conn.get());
            }
        });

        Thread t2 = executor.newThread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Connection 2 setting value...");
                conn.set("Connection 2");
            }
        });

        t1.start();
        Thread.sleep(100);
        t2.start();
    }
}
