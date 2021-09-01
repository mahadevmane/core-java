package org.open.corejava.jbasics.imageop;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HappyDiwali {

    public static void main(String[] args) {
        String fileName = "images/Diwali.jpg";
        Resource resource = new ClassPathResource(fileName);

        if (resource.exists()) {
            File f = new File(fileName);
            Desktop d = Desktop.getDesktop();
            try {
                d.open(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
