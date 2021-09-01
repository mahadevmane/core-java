package org.open.corejava.jbasics.multitread;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemo {

    public static void main(String[] args) {
        System.out.println("Main started...");
        String str = "Synechron";
        UsingThread ut1 = new UsingThread(str);
        UsingThread ut2 = new UsingThread(str);
        ut1.setName("Using Thread 1");
        ut2.setName("Using Thread 2");
        ut1.start();
        ut2.start();

        List<Contact> list = new ArrayList<Contact>();
        list.add(new Contact());
        list.add(new Contact("Savalaram", "9763894030"));
        list.add(new Contact("Sagar", "9850894090"));
        list.add(new Contact("Ajay", "9423897890"));
        list.add(new Contact("Omkar", "9890894030"));
        list.add(new Contact("Vishal", "9850894532"));

        UsingRunnable ur = new UsingRunnable(list);
        Thread t = new Thread(ur);
        t.setName("Using Runnable");
        t.start();

        ThreadFun tf = new ThreadFun();
        tf.start();
        Thread tt = new Thread(tf);
        tt.start();

        System.out.println("Main exited...");
    }
}
