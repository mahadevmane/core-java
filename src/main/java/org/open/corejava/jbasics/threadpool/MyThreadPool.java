package org.open.corejava.jbasics.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {
	BlockingQueue<Runnable> taskPool = new ArrayBlockingQueue<Runnable>(5);

	public MyThreadPool(int poolSize) {
		for (int i = 0; i < poolSize; i++)
			new Thread(new WokerThreads()).start();
	}

	public void execute(Runnable task) {
		try {
			taskPool.put(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class WokerThreads implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Runnable task = taskPool.take();
					task.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
