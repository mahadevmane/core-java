package org.open.corejava.notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class SaveItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		BufferedWriter bw = null;
		JFileChooser fc = new JFileChooser();
		int action = fc.showSaveDialog(null);

		if (action == JFileChooser.APPROVE_OPTION) {
			char[] data = Notepad.notePad.getText().toCharArray();
			try {
				bw = new BufferedWriter(new FileWriter(fc.getSelectedFile()));

				for (int i = 0; i < data.length; i++) {
					if (data[i] == '\n')
						bw.newLine();
					else
						bw.append(data[i]);
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

}
