package org.open.corejava.jbasics.annotations;

import java.lang.reflect.Method;

@MyAnnotation(author = "Mahadev", date = "22/7/2011, 23/7/2012")
public class TestAnnotation {

	@MyAnnotation(author = "Mahadev", date = "22/7/2012")
	public static void testMethod() {
		System.out.println("Welcome to Java");
		System.out.println("This is an example of Annotations...");
	}

	public static void main(String[] args) {
		testMethod();
		showAnnotations();
	}

	public static void showAnnotations() {
		TestAnnotation test = new TestAnnotation();
		try {
			Class<? extends TestAnnotation> c = test.getClass();
			Method m = c.getMethod("testMethod");

			MyAnnotation annotation1 = c.getAnnotation(MyAnnotation.class);
			MyAnnotation annotation2 = m.getAnnotation(MyAnnotation.class);

			System.out.println("Author of the class: " + annotation1.author());
			System.out.println("Date of Writing the class: " + annotation1.date());
			System.out.println("Author of the method: " + annotation2.author());
			System.out.println("Date of Writing the method: " + annotation2.date());
		} catch (NoSuchMethodException ex) {
			System.out.println("Invalid Method..." + ex.getMessage());
		}
	}
}