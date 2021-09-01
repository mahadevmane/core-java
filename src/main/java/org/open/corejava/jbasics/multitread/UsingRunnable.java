package org.open.corejava.jbasics.multitread;

import java.util.Iterator;
import java.util.List;

public class UsingRunnable implements Runnable {
    List<Contact> lst;

    public UsingRunnable(List<Contact> list) {
        lst = list;
    }

    @Override
    public void run() {
        String thread = Thread.currentThread().getName();
        for (Iterator<Contact> itr = lst.iterator(); itr.hasNext(); ) {
            System.out.println("Thread Name: " + thread + ", " + itr.next());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
