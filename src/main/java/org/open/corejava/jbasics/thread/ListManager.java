package org.open.corejava.jbasics.thread;

public class ListManager {

    public static void main(String[] args) throws InterruptedException {
        final Thread t = Thread.currentThread();

        new Thread() {
            /* Anonymous Constructor */ {
                setDaemon(true);
            }

            public void run() {
                while (true) {
                    System.out.println(t.getState());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Thread t1 = new Thread(new ListProducer());
        t1.start();
        Thread.sleep(3000);
        t1.join();
        System.out.println("After join...");
    }
}

class ListProducer implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
