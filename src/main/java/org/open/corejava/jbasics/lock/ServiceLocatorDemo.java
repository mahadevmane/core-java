package org.open.corejava.jbasics.lock;

public class ServiceLocatorDemo {
    public static void main(String[] args) {
        ServiceLocator.getInstance();
        ServiceLocator.getInstance();
        ServiceLocator.getInstance();
        ServiceLocator.doSomething();
        ServiceLocator.doSomething();
        ServiceLocator.doSomething();
    }
}
