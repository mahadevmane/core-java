package org.open.corejava.mime.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyShell extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    static long counter, startTime, srcSize, destSize;
    static double progress;
    static JProgressBar pBar;
    private final ExecutorService es = Executors.newFixedThreadPool(22);
    private final JPanel outer;
    private final JPanel jp1;
    private final JPanel jp2;
    private final JTextField srcPath;
    private final JTextField destPath;
    private final JButton srcBrowser;
    private final JButton destBrowser;
    private final JButton startCopying;
    private final JButton stopCopying;
    private String sourcePath = null, destinationPath = null;

    public CopyShell() {
        setTitle("Mime...[Mp]");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                es.shutdown();
                System.exit(0);
            }
        });

        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        jp1 = new JPanel();
        jp1.add(new JLabel("Source :        "));

        srcPath = new JTextField(27);
        srcPath.setEditable(false);
        jp1.add(srcPath);

        srcBrowser = new JButton("Browse...");
        srcBrowser.setActionCommand("Source");
        srcBrowser.addActionListener(this);
        jp1.add(srcBrowser);

        jp1.add(new JLabel("Destination :"));

        destPath = new JTextField(27);
        destPath.setEditable(false);
        jp1.add(destPath);

        destBrowser = new JButton("Browse...");
        destBrowser.setActionCommand("Destination");
        destBrowser.addActionListener(this);
        jp1.add(destBrowser);

        startCopying = new JButton("Start Copying...");
        startCopying.addActionListener(this);
        jp1.add(startCopying);

        stopCopying = new JButton("Cancel");
        stopCopying.addActionListener(this);
        jp1.add(stopCopying);

        jp2 = new JPanel(new BorderLayout());
        UIManager.put("ProgressBar.selectionBackground", Color.black);
        UIManager.put("ProgressBar.selectionForeground", Color.white);
        UIManager.put("ProgressBar.foreground", new Color(7, 190, 22));
        pBar = new JProgressBar();
        pBar.setStringPainted(true);
        jp2.add(pBar);
        pBar.setVisible(false);

        outer = new JPanel(new BorderLayout());
        outer.add(jp1, BorderLayout.CENTER);
        outer.add(jp2, BorderLayout.SOUTH);
        add(outer);
    }

    public static void main(String[] args) {
        JFrame cs = new CopyShell();
        cs.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int action;

        if (cmd.equals("Source")) {
            JFileChooser fcSrc = new JFileChooser();
            fcSrc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fcSrc.setDialogTitle("Select Source");
            fcSrc.setCurrentDirectory(new File(srcPath.getText()).getParentFile());
            action = fcSrc.showOpenDialog(this);
            if (action == JFileChooser.APPROVE_OPTION) {
                srcPath.setText(fcSrc.getSelectedFile().getPath());
            }
        }

        if (cmd.equals("Destination")) {
            JFileChooser fcDest = new JFileChooser();
            fcDest.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fcDest.setDialogTitle("Select Destination");
            fcDest.setCurrentDirectory(new File(destPath.getText()));
            action = fcDest.showOpenDialog(this);
            if (action == JFileChooser.APPROVE_OPTION) {
                destPath.setText(fcDest.getSelectedFile().getPath());
            }
        }

        if (cmd.equals("Start Copying...")) {
            WhatToDoDialog.setAll(false);
            counter = srcSize = destSize = 0;
            progress = 0;
            sourcePath = srcPath.getText();
            destinationPath = destPath.getText();
            File source = new File(sourcePath), destination = new File(destinationPath);

            if (sourcePath.equals("") || destinationPath.equals("")) {
                JOptionPane.showMessageDialog(null, "Source or Destination must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if (source.isDirectory())
                    findSize(source);
                else {
                    counter = 1;
                    srcSize = source.length();
                }
                pBar.setVisible(true);
                pBar.setValue(0);
                startTime = System.currentTimeMillis();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                if (isSubDirectory(new File(sourcePath), new File(destinationPath))) {
                    JOptionPane.showMessageDialog(null, "The destination is the same or subfolder of the source folder.", "Wrong Selection", JOptionPane.ERROR_MESSAGE);
                    destPath.setText("");
                } else {
                    if (source.isDirectory()) {
                        es.execute(new CopyDirectory(source, new File(destination, source.getName()), es));
                    } else {
                        es.execute(new CopyFile(source, new File(destination, source.getName())));
                    }
                }
            }
        }

        if (cmd.equals("Cancel")) {
            System.exit(0);
        }
    }

    private boolean isSubDirectory(File src, File dest) {
        while (dest != null) {
            if (dest.equals(src))
                return true;
            dest = dest.getParentFile();
        }
        return false;
    }

    private void findSize(File source) {
        File[] files = source.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findSize(file);
            } else {
                counter++;
                srcSize += file.length();
            }
        }
    }
}
