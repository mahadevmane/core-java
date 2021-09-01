package org.open.corejava.jbasics.resourcebundle;

public class Resource_fr_FR extends Resource {

    public Object handleGetObject(String key) {
        if (key.equals("Goodbye"))
            return "Au Revoir";
        if (key.equals("Hello"))
            return "Bonjour";

        return null;
    }
}