package org.open.corejava.mime.core;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class CopyFile implements Runnable {
	private static final Object sync = new Object();
	private static final Object obj = new Object();
	private FileChannel sc, tc;
	private final File source;
	private File destination;

	public CopyFile(File source, File destination) {
		this.source = source;
		this.destination = destination;
	}

	@Override
	public void run() {
		if (destination.exists()) {
			synchronized (sync) {
				if (!WhatToDoDialog.isAll()) {
					WhatToDoDialog.setReNamePolicy(0);
					WhatToDoDialog.setRenameBy(null);
					JDialog jd = new WhatToDoDialog(null, "What's you want to do?", true, destination.getPath());
					jd.setVisible(true);
				}
				switch (WhatToDoDialog.getOption()) {
				case 0:
					break;
				case 1:
					copyFile();
					break;
				case 2:
					int renamePolicy = WhatToDoDialog.getReNamePolicy();
					if (renamePolicy == 1)
						renameByPrefix();
					else if (renamePolicy == 2)
						renameByPostfix();
					else
						renameFile();
					copyFile();
					break;
				}
			}
		} else {
			copyFile();
		}
		synchronized (obj) {
			CopyShell.destSize += destination.length();
			CopyShell.progress = (CopyShell.destSize / (double) CopyShell.srcSize) * 100;
			CopyShell.pBar.setValue((int) CopyShell.progress);
		}
		if (CopyShell.srcSize == CopyShell.destSize) {
			long tReqTime = System.currentTimeMillis() - CopyShell.startTime;
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null,
					CopyShell.counter + " files copied successfully within " + tReqTime / 1000 + " seconds.", "",
					JOptionPane.INFORMATION_MESSAGE);
			CopyShell.pBar.setVisible(false);
		}
	}

	private void renameByPostfix() {
		String parentPath = destination.getParentFile().getPath();
		StringBuffer fName = new StringBuffer(destination.getName());
		fName.insert(fName.lastIndexOf("."), " - " + WhatToDoDialog.getRenameBy());
		destination = new File(parentPath + "\\" + fName);
		if (destination.exists())
			renameFile();
	}

	private void renameByPrefix() {
		String parentPath = destination.getParentFile().getPath();
		StringBuffer fName = new StringBuffer(WhatToDoDialog.getRenameBy() + " - " + destination.getName());
		destination = new File(parentPath + "\\" + fName);
		if (destination.exists())
			renameFile();
	}

	private void renameFile() {
		boolean flag = false;
		int i, cnt = 0;
		String parentPath = destination.getParentFile().getPath();
		StringBuffer fName = new StringBuffer(destination.getName());

		int start = fName.lastIndexOf("(");
		int end = fName.lastIndexOf(")");

		for (i = start + 1; i < end; i++) {
			flag = true;
			int ch = fName.charAt(i) - 48;
			if (ch >= 0 && ch <= 9)
				cnt = (cnt * 10) + ch;
			else
				break;
		}

		if (i < end || !flag)
			fName.insert(fName.lastIndexOf("."), " (2)");
		else
			fName.replace(start, end, "(" + ++cnt);

		destination = new File(parentPath + "\\" + fName);
		if (destination.exists())
			renameFile();
	}

	@SuppressWarnings("resource")
	private void copyFile() {
		try {
			sc = new FileInputStream(source).getChannel();
			tc = new FileOutputStream(destination).getChannel();
			sc.transferTo(0, sc.size(), tc);
			sc.close();
			tc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}