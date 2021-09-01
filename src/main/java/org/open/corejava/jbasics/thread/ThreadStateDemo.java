package org.open.corejava.jbasics.thread;

public class ThreadStateDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is executing...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " completed...");
            }
        });

        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        Thread.sleep(200);
        System.out.println(t.getState());
        Thread.sleep(600);
        System.out.println(t.getState());
    }
}
