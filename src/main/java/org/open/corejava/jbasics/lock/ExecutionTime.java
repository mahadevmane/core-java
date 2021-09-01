package org.open.corejava.jbasics.lock;

import java.util.Random;

public class ExecutionTime {

	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();
		Thread[] t = new Thread[10];

		for (int i = 0; i < 10; i++)
			t[i] = new Thread(task);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++)
			t[i].start();
		for (int i = 0; i < 10; i++)
			t[i].join();
		long totalTime = System.currentTimeMillis() - start;

		System.out.println("Total time :" + totalTime);
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		Random r = new Random();
		System.out.println("Hi...");
		try {
			Thread.sleep(r.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}