package org.open.corejava.mime;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFile implements Runnable {
    private static final Object sync = new Object();
    private static final Object obj = new Object();
    private final File source;
    private File destination;

    public CopyFile(File source, File destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void run() {
        if (destination.exists()) {
            String fname = destination.getAbsolutePath();
            String ex = fname.substring(fname.lastIndexOf("."));
            synchronized (sync) {
                if (!WhatToDo.isAll()) {
                    if (ex.equals(".bmp") || ex.equals(".jif")
                            || ex.equals(".jpg") || ex.equals(".png")
                            || ex.equals(".psd") || ex.equals(".thm")
                            || ex.equals(".tif") || ex.equals(".yuv")
                            || ex.equals(".ai") || ex.equals(".drw")
                            || ex.equals(".eps") || ex.equals(".ps")
                            || ex.equals(".svg")) {
                        try {
                            WhatToDo.img1 = new JLabel(new ImageIcon(
                                    ImageIO.read(source)));
                            WhatToDo.img1
                                    .setToolTipText("New Image you trying to copy.");
                            WhatToDo.img2 = new JLabel(new ImageIcon(
                                    ImageIO.read(destination)));
                            WhatToDo.img2.setToolTipText("Existing Image");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            WhatToDo.img1 = new JLabel(new ImageIcon(
                                    ImageIO.read(new File("Image.gif"))));
                            WhatToDo.img2 = new JLabel(new ImageIcon(
                                    ImageIO.read(new File("Image.gif"))));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    JDialog jd = new WhatToDo(null, "What's you want to do?",
                            true, destination.getPath());
                    WhatToDo.reNamePolicy
                            .setSelectedIndex(CopyShell.renamePolicy);
                    WhatToDo.rnp.setText(CopyShell.renameBy);
                    jd.setVisible(true);
                }
                switch (WhatToDo.getOption()) {
                    case 0:
                        break;
                    case 1:
                        copyFile();
                        break;
                    case 2:
                        int renamePolicy = CopyShell.renamePolicy;
                        if (renamePolicy == 1)
                            renameByPrefix();
                        else if (renamePolicy == 2)
                            renameByPostfix();
                        else
                            renameFile();
                        copyFile();
                        break;
                }
            }
        } else {
            copyFile();
        }
        synchronized (obj) {
            CopyShell.destSize += destination.length();
            CopyShell.progress = (CopyShell.destSize / (double) CopyShell.srcSize) * 100;
            CopyShell.pBar.setValue((int) CopyShell.progress);
        }
        if (CopyShell.srcSize == CopyShell.destSize) {
            long tReqTime = System.currentTimeMillis() - CopyShell.startTime;
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, CopyShell.counter
                    + " files copied successfully within " + tReqTime / 1000
                    + " seconds.", "", JOptionPane.INFORMATION_MESSAGE);
            CopyShell.pBar.setVisible(false);
        }
    }

    private void renameByPostfix() {
        String parentPath = destination.getParentFile().getPath();
        StringBuilder fName = new StringBuilder(destination.getName());
        fName.insert(fName.lastIndexOf("."), WhatToDo.rnp.getText());
        destination = new File(parentPath + "\\" + fName);
        if (destination.exists())
            renameFile();
    }

    private void renameByPrefix() {
        String parentPath = destination.getParentFile().getPath();
        destination = new File(parentPath + "\\" + WhatToDo.rnp.getText() + destination.getName());
        if (destination.exists()) {
            renameFile();
        }
    }

    private void renameFile() {
        boolean flag = false;
        int i, cnt = 0;
        String parentPath = destination.getParentFile().getPath();
        StringBuilder fName = new StringBuilder(destination.getName());

        int start = fName.lastIndexOf("(");
        int end = fName.lastIndexOf(")");

        for (i = start + 1; i < end; i++) {
            flag = true;
            int ch = fName.charAt(i) - 48;
            if (ch >= 0 && ch <= 9)
                cnt = (cnt * 10) + ch;
            else
                break;
        }

        if (i < end || !flag)
            fName.insert(fName.lastIndexOf("."), " (2)");
        else
            fName.replace(start, end, "(" + ++cnt);

        destination = new File(parentPath + "\\" + fName);
        if (destination.exists())
            renameFile();
    }

    @SuppressWarnings("resource")
    private void copyFile() {
        try {
            FileChannel sc = new FileInputStream(source).getChannel();
            FileChannel tc = new FileOutputStream(destination).getChannel();
            sc.transferTo(0, sc.size(), tc);
            sc.close();
            tc.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}