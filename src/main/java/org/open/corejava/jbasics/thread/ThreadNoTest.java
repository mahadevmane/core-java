package org.open.corejava.jbasics.thread;

public class ThreadNoTest {

	public static void main(String[] args) {
		int cnt = 0;
		try {
			while (true) {
				cnt++;
				new Thread() {
					public void run() {
						System.out.println(Thread.currentThread().getName());
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Counter: " + cnt);
	}
}
