package org.open.corejava.jbasics.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtilities {

	public static final void copy(File source, File destination) throws IOException {
		if (source.isDirectory()) {
			copyDirectory(source, destination);
		} else {
			copyFile(source, destination);
		}
	}

	public static final void copyDirectory(File source, File destination) throws IOException {
		if (!destination.exists())
			destination.mkdirs();

		File[] files = source.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				copyDirectory(file, new File(destination, file.getName()));
			} else {
				copyFile(file, new File(destination, file.getName()));
			}
		}
	}

	@SuppressWarnings("resource")
	public static final void copyFile(File source, File destination) throws IOException {
		FileChannel sc = new FileInputStream(source).getChannel();
		FileChannel tc = new FileOutputStream(destination).getChannel();
		sc.transferTo(0, sc.size(), tc);
		sc.close();
		tc.close();
	}
}