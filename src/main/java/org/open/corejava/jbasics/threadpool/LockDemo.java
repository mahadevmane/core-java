package org.open.corejava.jbasics.threadpool;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

	public static void main(String[] args) {
		final CustomIntegerArray ci = new CustomIntegerArray();

		new Thread() {
			public void run() {
				while (true) {
					for (int i = 0; i < 3; i++) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(ci.getValue(i) + " value get from " + i);
					}
				}
			}
        }.start();

		new Thread() {
			public void run() {
				Random r = new Random();
				while (true) {
					for (int i = 0; i < 3; i++) {
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ci.setValue(i, r.nextInt(100));
						System.out.println("Value set at: " + i);
					}
				}
			}
        }.start();
	}
}

class CustomIntegerArray {
	private final int[] arr = new int[3];
	Lock lock = new ReentrantLock();

	public void setValue(int pos, int value) {
		boolean success = false;
		try {
			if (success = lock.tryLock())
				arr[pos] = value;
		} finally {
			if (success)
				lock.unlock();
		}
	}

	public int getValue(int pos) {
		int value = -1;
		try {
			if (lock.tryLock())
				value = arr[pos];
		} finally {
			lock.unlock();
		}
		return value;
	}
}