package org.open.corejava.notepad;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ZoeloeSoft.projects.JFontChooser.JFontChooser;

public class FontItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFontChooser fc = new JFontChooser(null);
		int result = fc.showDialog();

		if (result == JFontChooser.OK_OPTION) {
			Font font = fc.getFont();
			Notepad.notePad.setFont(font);
		}
	}

}
