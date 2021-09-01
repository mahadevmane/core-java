package org.open.corejava.longhierachydeleter;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteAction implements Runnable {
    private static final List<File> lst = new ArrayList<File>();
    private static long totalFolders, totalFiles, totalFNF;
    private static double cnt, inc;

    private static void listFile(File f) {
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                listFile(file);
                lst.add(file);
            } else {
                file.delete();
                inc = (++cnt / totalFNF) * 100;
                Delete.progress.setValue((int) inc);
            }
        }
    }

    private static void deleteFile() {
        for (Iterator<File> i = lst.iterator(); i.hasNext(); ) {
            i.next().delete();
            inc = (++cnt / totalFNF) * 100;
            Delete.progress.setValue((int) inc);
        }
    }

    @Override
    public void run() {
        File file = new File(Delete.path.getText());
        totalFiles = totalFolders = 0;
        countFilesNFolders(file);
        totalFNF = totalFiles + totalFolders + 1;
        cnt = 0;
        Delete.progress.setVisible(true);
        Delete.progress.setValue(0);
        double startTime = System.currentTimeMillis();
        if (file.isDirectory())
            listFile(file);
        lst.add(file);
        deleteFile();
        double reqTime = (System.currentTimeMillis() - startTime) / 1000;
        JOptionPane.showMessageDialog(null, totalFiles + " files and "
                + totalFolders + " folders deleted within " + (int) reqTime
                + " seconds.", "Deleted...", JOptionPane.INFORMATION_MESSAGE);
        Delete.path.setText("");
        Delete.progress.setVisible(false);
    }

    protected void countFilesNFolders(File f) {
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.isFile())
                totalFiles++;
            else {
                totalFolders++;
                countFilesNFolders(file);
            }
        }
    }
}
