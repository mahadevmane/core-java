package org.open.corejava.jbasics.thread;

public class ObjectMonitor {

    public static void main(String[] args) throws InterruptedException {
        final Customer cust = new Customer();

        Thread t1 = new Thread() {
            public void run() {
                cust.read();
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                cust.update();
            }
        };

        t1.start();
        Thread.sleep(200);
        t2.start();
    }

}

class Customer {

    synchronized public void read() {
        System.out.println(Thread.currentThread().getName() + " is reading...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void update() {
        System.out.println(Thread.currentThread().getName() + " is updating...");
    }

}