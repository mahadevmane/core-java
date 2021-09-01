package org.open.corejava.notepad;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		PrinterJob job = PrinterJob.getPrinterJob();
		final String data = Notepad.notePad.getText();
		job.setPrintable(new Printable() {

			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				graphics.drawString(data, 10, 10);
				return Printable.PAGE_EXISTS;
			}
		});

		if (job.printDialog()) {
			try {
				System.out.println(data);
				job.print();
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
	}
}
