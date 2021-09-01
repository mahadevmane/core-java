package org.open.corejava.notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

public class OpenItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		BufferedReader data;
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		/*fc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public boolean accept(File f) {
				return f.getPath().endsWith(".txt");
			}
		});*/

		int action = fc.showOpenDialog(null);

		if (action == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				String line;
				data = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				while ((line = data.readLine()) != null)
					Notepad.notePad.append(line + "\n");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
