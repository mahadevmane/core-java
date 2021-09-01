package org.open.corejava.jbasics.resourcebundle;

import java.util.Enumeration;
import java.util.StringTokenizer;

public class Resource extends java.util.ResourceBundle {
	String keys = "Hello Goodbye";

	public Object handleGetObject(String key) {
		if (key.equals("Hello")) {
			return "Hello";
		}
		if (key.equals("Goodbye")) {
			return "Goodbye";
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Enumeration getKeys() {
		StringTokenizer key = new StringTokenizer(keys);
		return key;
	}
}