package org.open.corejava.jbasics.synchronizers;

import java.util.Random;

public class OwnSynchronizerLatch {

	public static void main(String[] args) {
		final LatchWithWaitNotify latch = new LatchWithWaitNotify(10);
		Thread[] thread = new Thread[10];

		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread() {
				@Override
				public void run() {
					Random r = new Random();
					System.out.println("Hi...");
					try {
						Thread.sleep(r.nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					latch.countDown();
				}
			};
		}

		for (int i = 0; i < 10; i++)
			thread[i].start();

		long start = System.currentTimeMillis();
		latch.awaitZero();
		System.out.println("Total time :" + (System.currentTimeMillis() - start));
	}
}

class LatchWithWaitNotify {
	private final Object lock = new Object();
	private int count;

	public LatchWithWaitNotify(int cnt) {
		count = cnt;
	}

	public void countDown() {
		synchronized (lock) {
			if (--count == 0)
				lock.notify();
		}
	}

	public void awaitZero() {
		synchronized (lock) {
			while (count > 0) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}