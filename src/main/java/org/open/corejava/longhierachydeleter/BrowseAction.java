package org.open.corejava.longhierachydeleter;

import javax.swing.*;

public class BrowseAction implements Runnable {

    @Override
    public void run() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int action = fc.showOpenDialog(null);
        if (action == JFileChooser.APPROVE_OPTION)
            Delete.path.setText(fc.getSelectedFile().getPath());
    }

}
