package org.open.corejava.jbasics.lock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(7221);
		System.out.println("Server Started...");

		ThreadPoolExecutor es = new ThreadPoolExecutor(5, 7, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
				new ThreadFactory() {

					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r);
					}
				}, new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println("Request rejected...");
					}
				});

		for (int i = 0; i < 3; i++)
			es.execute(new RequestListen(ss, es));
	}
}

class RequestListen implements Runnable {
	ServerSocket ss;
	ExecutorService es;

	public RequestListen(ServerSocket ss, ExecutorService es) {
		this.ss = ss;
		this.es = es;
	}

	@Override
	public void run() {
		while (true) {
			try {
				while (true) {
					Socket socket = ss.accept();
					System.out.println("Listener: " + Thread.currentThread().getName());
					es.execute(new RequestProcess(socket));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class RequestProcess implements Runnable {
	Socket socket;

	public RequestProcess(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Processor: " + Thread.currentThread().getName());
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			int req;
			while (true) {
				req = in.read();
				System.out.print((char) req);
				if (in.available() == 0) {
					break;
				}
			}
			System.out.println("\n**********************\n\n\n");

			String res = "<html><body><h1>Welcome</h1></body></html>";
			out.write(res.getBytes());

			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
