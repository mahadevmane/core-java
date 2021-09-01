package org.open.corejava.jbasics.synchronizers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureObjectDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(1);
		Future<String> fObj = service.submit(new CustomerCacheCreator());

		// Thread.sleep(5000);

		System.out.println("Getting the the value from future object...");
		System.out.println(fObj.get());

		service.shutdown();
	}
}

class CustomerCacheCreator implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		return "List Done...";
	}

}