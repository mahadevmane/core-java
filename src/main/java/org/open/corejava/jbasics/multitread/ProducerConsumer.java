package org.open.corejava.jbasics.multitread;

import java.util.*;
import java.io.*;

public class ProducerConsumer {
	protected LinkedList<Object> list = new LinkedList<Object>();
	protected int MAX = 10;
	protected boolean done = false;

	ProducerConsumer(int nP, int nC) {
		for (int i = 0; i < nP; i++)
			new Producer().start();
		for (int i = 0; i < nC; i++)
			new Consumer().start();
	}

	/** Inner class representing the Producer side */
	class Producer extends Thread {

		public void run() {
			while (true) {
				Object justProduced = getRequestFromNetwork();
				synchronized (list) {
					while (list.size() == MAX)
						try {
							System.out.println("Producer Wating...");
							list.wait();
						} catch (InterruptedException ex) {
							System.out.println("Producer Interrupted.");
						}
					list.addFirst(justProduced);
					list.notifyAll();
					System.out.println("Produced by " + getName() + "; List Size: " + list.size());
					if (done)
						break;
					/* yield(); /* Useful for green threads & demo programs. */
				}
			}
		}

		Object getRequestFromNetwork() { /* Simulation of reading from client */
			try {
				Thread.sleep(10); /* Simulate time passing during read */
			} catch (InterruptedException ex) {
				System.out.println("Producer Read Interrupted.");
			}
			return (new Object());
		}
	}

	/** Inner class representing the Consumer side */
	class Consumer extends Thread {
		public void run() {
			while (true) {
				Object obj = null;
				synchronized (list) {
					while (list.size() == 0) {
						try {
							System.out.println("Consumer Wating...");
							list.wait();
						} catch (InterruptedException ex) {
							System.out.println("Consumer Interrupted.");
						}
					}
					obj = list.removeLast();
					list.notifyAll();
					if (done)
						break;
				}
				System.out.println("Consuming object " + obj + " by " + getName() + "; List Size:" + list.size());
				/* yield(); */
			}
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		int numProducers = 4;
		int numConsumers = 3;
		ProducerConsumer pc = new ProducerConsumer(numProducers, numConsumers);

		Thread.sleep(300);
		synchronized (pc.list) {
			pc.done = true;
			pc.list.notifyAll();
		}
	}
}
