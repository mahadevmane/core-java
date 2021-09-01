package org.open.corejava.jbasics.resourcebundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoggerResourseBundleDemo {

    public static void main(String[] args) {
        try {
            Locale[] locales = new Locale[]{new Locale("fr", "FR"), new Locale("en", "IN")};
            ResourceBundle bundle = null;
            for (int i = 0; i < locales.length; i++) {
                bundle = ResourceBundle.getBundle("mahadev.resourceBundle.Resource", locales[i]);

                System.out.println(locales[i].getDisplayName());
                System.out.println(bundle.getString("Hello"));
                System.out.println(bundle.getString("Goodbye") + "\n");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
