package org.open.corejava.jbasics.maps;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapMultiThreadDemo {

	public static void main(String[] args) {
		ConcurrentHashMap<Integer, String> hm = new ConcurrentHashMap<Integer, String>();
		Thread t1 = new Thread(new HashMapRead(hm));
		Thread t2 = new Thread(new HashMapWrite(hm));

		t1.start();
		t2.start();
	}
}

class HashMapRead implements Runnable {

	private final ConcurrentHashMap<Integer, String> hm;

	public HashMapRead(ConcurrentHashMap<Integer, String> hm) {
		this.hm = hm;
	}

	@Override
	public void run() {
		while (true) {
			Set<?> ss = hm.entrySet();
			for (Iterator<?> iterator = ss.iterator(); iterator.hasNext();) {
				System.out.println(iterator.next());

			}
		}
	}
}

class HashMapWrite implements Runnable {
	private final ConcurrentHashMap<Integer, String> hm;

	public HashMapWrite(ConcurrentHashMap<Integer, String> hm) {
		this.hm = hm;
	}

	@Override
	public void run() {
		Random r = new Random();
		while (true) {
			hm.put(r.nextInt(722), "Mahadev " + r.nextInt(100));
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}