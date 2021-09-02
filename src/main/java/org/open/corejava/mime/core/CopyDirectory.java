package org.open.corejava.mime.core;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * @author Mahadev Mane
 */

public class CopyDirectory implements Runnable {
    private final File source;
    private final File destination;
    private final ExecutorService es;

    public CopyDirectory(File source, File destination, ExecutorService es) {
        this.source = source;
        this.destination = destination;
        this.es = es;
    }

    @Override
    public void run() {
        if (!destination.exists()) {
            destination.mkdirs();
        }

        File[] files = source.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                es.execute(new CopyDirectory(file, new File(destination, file.getName()), es));
            } else {
                es.execute(new CopyFile(file, new File(destination, file.getName())));
            }
        }
    }
}
