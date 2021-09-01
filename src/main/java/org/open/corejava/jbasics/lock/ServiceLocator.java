package org.open.corejava.jbasics.lock;

public class ServiceLocator {
	private ServiceLocator() {
		System.out.println("ServiceLocator is instantiated..");
	}

	private static class SingletonHolder {
		static {
			System.out.println("SingletonHolder is loaded...");
		}
		private static final ServiceLocator me = new ServiceLocator();
	}

	public static ServiceLocator getInstance() {
		return SingletonHolder.me;
	}

	public static void doSomething() {
		System.out.println("Hi...");
	}

}
