package org.open.corejava.jbasics.thread;

public class BlockingStateDemo {
	public static void main(String[] args) {
		final Product product = new Product();

		final Thread t1 = new Thread() {
			@Override
			public void run() {
				product.readProduct();
			}
		};

		final Thread t2 = new Thread() {
			@Override
			public void run() {
				product.putProduct();
			}
		};
		t1.start();
		System.out.println(t2.getState() + " " + t2.isAlive());
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		t2.start();

		new Thread() {
			{
				setDaemon(true);
			}

			public void run() {
				while (true) {
					System.out.println(t1.getState() + "\t" + t2.getState() + " " + t2.isAlive());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}

class Product {
	synchronized public void readProduct() {
		System.out.println("Getting product by " + Thread.currentThread().getName());
		try {
			Thread.sleep(3000); /* Does not release the lock */
			wait(); /* Release the lock */
			System.out.println(Thread.currentThread().getName() + " is released from wait set.");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public void putProduct() {
		System.out.println("Putting product by " + Thread.currentThread().getName());
		notify(); /* Awake thread from wait set */
	}
}