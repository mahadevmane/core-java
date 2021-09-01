package org.open.corejava.jbasics.synchronizers;

import java.util.concurrent.Semaphore;

public class Pool {
    private static final int MAX_AVAILABLE = 3;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
    protected Object[] items = new Object[]{"Connection 1", "Connection 2", "Connection 3"};
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    // Not a particularly efficient data structure; just for demo

    public static void main(String[] args) throws Exception {
        final Pool p = new Pool();

        Object o1 = p.getItem();
        System.out.println(o1);
        Object o2 = p.getItem();
        System.out.println(o2);
        Object o3 = p.getItem();
        System.out.println(o3);

        p.putItem(o2);
        p.releaseAllConnections();
        System.out.println("After release");
        Object o4 = p.getItem();
        System.out.println(o4);

        System.out.println("--- End ---");

    }

    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object x) {
        if (markAsUnused(x))
            available.release();
    }

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // Not reached
    }

    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item.equals(items[i])) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    public void releaseAllConnections() {
        available.release(3);
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            used[i] = false;
        }

    }

    public void blockConnectionPool() {
        available.drainPermits();
    }

}