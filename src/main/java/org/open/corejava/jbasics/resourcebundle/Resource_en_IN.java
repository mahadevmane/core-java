package org.open.corejava.jbasics.resourcebundle;

public class Resource_en_IN extends Resource {

    public Object handleGetObject(String key) {
        if (key.equals("Goodbye"))
            return "Have a nice day ...";
        if (key.equals("Hello"))
            return "Hi, How are you ...";

        return null;
    }
}