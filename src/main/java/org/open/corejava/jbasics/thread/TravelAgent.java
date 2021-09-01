package org.open.corejava.jbasics.thread;

public class TravelAgent {

	public static void main(String[] args) {
		System.out.println("Starting Thread Name: " + Thread.currentThread().getName());

		HoneyMoon h1 = new HoneyMoon("HongKong");
		HoneyMoon h2 = new HoneyMoon("New York");

		Thread t1 = new Thread(h1);
		Thread t2 = new Thread(h2);

		t1.start();
		t2.start();
	}
}
