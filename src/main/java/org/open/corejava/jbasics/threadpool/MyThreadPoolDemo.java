package org.open.corejava.jbasics.threadpool;

public class MyThreadPoolDemo {

	public static void main(String[] args) {
		MyThreadPool threadPool = new MyThreadPool(5);
		int i = 0;
		while (i++ < 20) {
			threadPool.execute(new Task());
		}
	}
}

class Task implements Runnable {
	private static int cnt;

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is executing task :" + cnt++);
	}
}