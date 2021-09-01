package org.open.corejava.jbasics.synchronizers;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public static void main(String[] args) throws InterruptedException {
		final Semaphore s = new Semaphore(3);

		s.acquire();
		s.acquire(2);

		new Thread() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				s.release();
			}
        }.start();

		System.out.println("Trying to acquire 4th time...");
		s.acquire();
		System.out.println("After acquire...");
	}
}
