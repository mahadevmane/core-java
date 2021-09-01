package org.open.corejava.mime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WhatToDo extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    static JLabel img1, img2;
    static JComboBox<?> reNamePolicy;
    static JTextField rnp;
    private static boolean all = false;
    private static int option = -1;
    private final JTextArea filePath;
    private final JButton skip;
    private final JButton replace;
    private final JButton rename;
    private final JButton cancel;
    private final JCheckBox opt;
    private final JPanel jp1;
    private final JPanel jp2;
    private final JPanel jp3;
    private final JScrollPane forImg1;
    private final JScrollPane forImg2;
    private final GridBagConstraints c;

    public WhatToDo(CopyShell copyFile, String title, boolean modal, String path) {
        super(copyFile, title, modal);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = .2;
        c.weighty = .2;
        c.fill = GridBagConstraints.BOTH;
        jp1 = new JPanel();
        jp1.setMaximumSize(jp1.getPreferredSize());
        jp1.add(new JLabel("Select Rename Policy: "));
        String[] items = {"Automatically", "Prefix By", "Postfix By"};
        reNamePolicy = new JComboBox<Object>(items);
        reNamePolicy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int policy = reNamePolicy.getSelectedIndex();
                if (policy == 0) {
                    rnp.setText("");
                    rnp.setEditable(false);
                } else
                    rnp.setEditable(true);
                CopyShell.renamePolicy = reNamePolicy.getSelectedIndex();
            }
        });
        jp1.add(reNamePolicy);

        rnp = new JTextField(12);
        jp1.add(rnp);

        filePath = new JTextArea(3, 45);
        filePath.setEditable(false);
        filePath.setLineWrap(true);
        filePath.setText("The file \" " + path + " \" is already exist...");
        jp1.add(filePath);
        add(jp1, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1, 2));
        jp2.setMaximumSize(jp2.getPreferredSize());
        forImg1 = new JScrollPane(img1);
        jp2.add(forImg1);
        forImg2 = new JScrollPane(img2);
        jp2.add(forImg2);
        add(jp2, c);

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = .1;
        c.weighty = .1;
        c.fill = GridBagConstraints.BOTH;
        jp3 = new JPanel();
        jp3.setMaximumSize(jp3.getPreferredSize());
        opt = new JCheckBox("Do, the same for other conflicts.");
        opt.setMnemonic('D');
        jp3.add(opt);

        skip = new JButton("Skip");
        skip.setMnemonic('S');
        skip.addActionListener(this);
        jp3.add(skip);

        replace = new JButton("Replace");
        replace.setMnemonic('R');
        replace.addActionListener(this);
        jp3.add(replace);

        rename = new JButton("Rename");
        rename.setMnemonic('e');
        rename.addActionListener(this);
        jp3.add(rename);

        cancel = new JButton("Cancel");
        cancel.setMnemonic('C');
        cancel.addActionListener(this);
        jp3.add(cancel);
        add(jp3, c);
    }

    public static boolean isAll() {
        return all;
    }

    public static void setAll(boolean all) {
        WhatToDo.all = all;
    }

    public static int getOption() {
        return option;
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
            CopyShell.renameBy = rnp.getText();
            if (CopyShell.renamePolicy != 0 && CopyShell.renameBy.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Please, provide rename text or change rename policy to \"Automatically\".", "Rename By",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.setVisible(false);
        }

        if (cmd.equals("Cancel"))
            System.exit(0);
    }
}
