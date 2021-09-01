package org.open.corejava.jbasics.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(25);
		ThreadsForReq th = new ThreadsForReq();
		for (int i = 0; i < 100; i++)
			es.execute(th);
	}
}

class ThreadsForReq implements Runnable {

	@Override
	public void run() {
		System.out.println("Requestor: " + Thread.currentThread().getName());
		Socket socket = null;
		try {
			socket = new Socket("localhost", 7221);
			socket.getOutputStream().write("Hi...".getBytes());
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException...");
		} catch (IOException e) {
			System.out.println("IOException...");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("IOException... while closing socket.");
			}
		}
	}

}