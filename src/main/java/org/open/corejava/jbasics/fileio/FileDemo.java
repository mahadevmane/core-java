package org.open.corejava.jbasics.fileio;

import java.io.File;
import java.io.IOException;

public class FileDemo {

    public static void main(String[] args) {
        File f = new File("File.txt");
        System.out.println("\n---File Demo---\n");
        try {
            System.out.println("Created: " + f.createNewFile());
            System.out.println("Exists: " + f.exists());
            System.out.println("File Name: " + f.getName());
            System.out.println("Parent: " + f.getParent());
            System.out.println("Path: " + f.getPath());
            System.out.println("Absolute Path: " + f.getAbsolutePath());
            System.out.println("Canonical Path: " + f.getCanonicalPath());
            System.out.println("Free Space: " + f.getFreeSpace() + " bytes");
            System.out.println("Total Space: " + f.getTotalSpace() + " bytes");
            System.out.println("Usable Space: " + f.getUsableSpace() + " bytes");
            System.out.println("Executable: " + f.canExecute());
            System.out.println("Is Directory: " + f.isDirectory());
        } catch (IOException e) {
            System.out.println("File not created...");
            e.printStackTrace();
        }
    }
}
