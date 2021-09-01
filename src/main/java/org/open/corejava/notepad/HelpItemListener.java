package org.open.corejava.notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class HelpItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "This is a simple notepad."
				+ "\nFor more detail contact on mahadevmane@gmail.com", "Help",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
