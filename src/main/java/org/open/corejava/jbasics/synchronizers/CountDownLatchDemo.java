package org.open.corejava.jbasics.synchronizers;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(10);
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

		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}

		long start = System.currentTimeMillis();
		latch.await();

		System.out.println("Total time :" + (System.currentTimeMillis() - start));
	}
}
