package org.open.corejava.jbasics.synchronizers;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerExample {

    final Exchanger<String> exchanger = new Exchanger<String>();

    public static void main(String[] args) {

        ExchangerExample e = new ExchangerExample();
        e.doWork();
    }

    public void doWork() {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.execute(new Runnable() {
            public void run() {
                String someData, result = null;

                while (true) {

                    try {
                        Thread.sleep(new Random().nextInt(3000));
                        someData = "Data From Thread 1";
                        result = exchanger.exchange(someData);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("In Thread 1 the result: " + result);

                }

            }
        });

        pool.execute(new Runnable() {
            public void run() {
                String someData, result = null;

                while (true) {

                    try {
                        Thread.sleep(new Random().nextInt(3000));
                        someData = "Data From Thread 2";
                        result = exchanger.exchange(someData);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("In thread 2 the result: " + result);

                }

            }
        });

    }

}