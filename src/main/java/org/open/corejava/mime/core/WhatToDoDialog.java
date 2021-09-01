package org.open.corejava.mime.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WhatToDoDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static boolean all = false;
    private static int option = -1;
    private static int reNamePolicy = 0;
    private static String renameBy = null;
    private final JTextArea filePath;
    private final JButton skip;
    private final JButton replace;
    private final JButton rename;
    private final JButton cancel;
    private final JCheckBox opt;
    private final JComboBox<?> rNamePolicy;

    public WhatToDoDialog(CopyShell copyFile, String title, boolean modal, String path) {
        super(copyFile, title, modal);
        setSize(550, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        add(new JLabel("Select Rename Policy: "));
        String[] items = {"Automatically", "By Prefix", "By Postfix"};
        rNamePolicy = new JComboBox<Object>(items);
        rNamePolicy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReNamePolicy(rNamePolicy.getSelectedIndex());
                if (reNamePolicy == 1 || reNamePolicy == 2) {
                    if (reNamePolicy == 1)
                        setRenameBy(JOptionPane.showInputDialog(null, "Prefixed By: ", "Prefixed By",
                                JOptionPane.DEFAULT_OPTION));
                    else
                        setRenameBy(JOptionPane.showInputDialog(null, "Postfixed By: ", "Postfixed By",
                                JOptionPane.DEFAULT_OPTION));
                }
                if (getRenameBy() == null) {
                    reNamePolicy = 0;
                    rNamePolicy.setSelectedIndex(0);
                }
            }
        });
        add(rNamePolicy);

        filePath = new JTextArea(3, 45);
        filePath.setEditable(false);
        filePath.setLineWrap(true);
        add(filePath);
        filePath.setText("The file \" " + path + " \" is already exist...");

        opt = new JCheckBox("Do, the same for other conflicts.");
        opt.setMnemonic('D');
        add(opt);

        skip = new JButton("Skip");
        skip.setMnemonic('S');
        skip.addActionListener(this);
        add(skip);

        replace = new JButton("Replace");
        replace.setMnemonic('R');
        replace.addActionListener(this);
        add(replace);

        rename = new JButton("Rename");
        rename.setMnemonic('e');
        rename.addActionListener(this);
        add(rename);

        cancel = new JButton("Cancel");
        cancel.setMnemonic('C');
        cancel.addActionListener(this);
        add(cancel);
    }

    public static boolean isAll() {
        return all;
    }

    public static void setAll(boolean all) {
        WhatToDoDialog.all = all;
    }

    public static int getOption() {
        return option;
    }

    public static int getReNamePolicy() {
        return reNamePolicy;
    }

    public static void setReNamePolicy(int reNamePolicy) {
        WhatToDoDialog.reNamePolicy = reNamePolicy;
    }

    public static String getRenameBy() {
        return renameBy;
    }

    public static void setRenameBy(String renameBy) {
        WhatToDoDialog.renameBy = renameBy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (opt.isSelected())
            all = true;

        if (cmd.equals("Skip")) {
            option = 0;
            this.setVisible(false);
        }

        if (cmd.equals("Replace")) {
            option = 1;
            this.setVisible(false);
        }

        if (cmd.equals("Rename")) {
            option = 2;
            this.setVisible(false);
        }

        if (cmd.equals("Cancel"))
            System.exit(0);
    }
}
