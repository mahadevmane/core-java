package org.open.corejava.jbasics.resourcebundle.anotherway;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class ResourceBundleTest {

    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle("mahadev.resourceBundle.anotherWay.Resource_in_In");
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = rb.getString(key);
            System.out.println(key + ": " + value);
        }
    }

}