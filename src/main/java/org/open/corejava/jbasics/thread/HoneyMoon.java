package org.open.corejava.jbasics.thread;

import java.util.Random;

public class HoneyMoon implements Runnable {
	private final String name;

	public HoneyMoon(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(r.nextInt(2000));
				System.out.println("Going to " + name + "\tThread Name: " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
