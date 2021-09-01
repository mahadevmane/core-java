package org.open.corejava.jbasics.lock;

public class ServiceLocator {
    private ServiceLocator() {
        System.out.println("ServiceLocator is instantiated..");
    }

    public static ServiceLocator getInstance() {
        return SingletonHolder.me;
    }

    public static void doSomething() {
        System.out.println("Hi...");
    }

    private static class SingletonHolder {
        private static final ServiceLocator me = new ServiceLocator();

        static {
            System.out.println("SingletonHolder is loaded...");
        }
    }

}
