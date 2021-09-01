package org.open.corejava.jbasics.thread;

import java.lang.reflect.Field;

public class AccessPrivateMemberOutside {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException, NoSuchFieldException {
		Class<?> c = Class.forName("HoneyMoon");
		Object o = c.newInstance();
		Field f = c.getDeclaredField("name");
		f.setAccessible(true);
		System.out.println(f.get(o));
	}
}
