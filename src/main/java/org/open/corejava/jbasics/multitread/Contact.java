package org.open.corejava.jbasics.multitread;

public class Contact {
    String name;
    String mob;

    public Contact() {
        name = "Mahadev";
        mob = "9404275209";
    }

    public Contact(String name, String mob) {
        this.name = name;
        this.mob = mob;
    }

    @Override
    public String toString() {
        return "Contact [Name: " + name + ", Mobile No.: " + mob + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

}
