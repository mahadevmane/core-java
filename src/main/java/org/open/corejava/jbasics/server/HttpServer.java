package org.open.corejava.jbasics.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(7221);
        System.out.println("Server Started...");
        ExecutorService es = Executors.newFixedThreadPool(15);

        for (int i = 0; i < 5; i++)
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
