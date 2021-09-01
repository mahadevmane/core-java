package org.open.corejava.notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Notepad.notePad.setText("");
	}
}
