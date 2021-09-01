package org.open.corejava.jbasics.thread;

public class InturruptDemo {

    public static void main(String[] args) {
        Thread t = new Thread(new Vacation());
        t.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }
}

class Vacation implements Runnable {

    @Override
    public void run() {
        // Random r = new Random();
        while (true) {
            System.out.println("Going to " + Thread.currentThread().getName());
            /*
             * try { Thread.sleep(r.nextInt(1000)); } catch
             * (InterruptedException e) { System.out.println(
             * "Thread is interrupted..."); break; }
             */
            if (Thread.interrupted()) {
                System.out.println(Thread.currentThread().getState());
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " is " + Thread.currentThread().isAlive());
    }
}