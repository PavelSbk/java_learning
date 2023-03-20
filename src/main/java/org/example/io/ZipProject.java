package org.example.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipProject {

    public static final String FILE = "C:\\Users\\User\\Desktop\\testDirectory\\test.zip";

    public static void main(String[] args) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(FILE));
        createZipDir(zos, new File(".").listFiles(), "");
    }

    private static void createZipDir(ZipOutputStream zos, File[] files, String path) throws IOException {
        String ps = File.pathSeparator;
        for (File f : files) {
            if (f.isDirectory()) {
                createZipDir(zos, f.listFiles(), path + f.getName() + ps);
            } else if (!f.getName().equals(FILE)) {
                ZipEntry ze = new ZipEntry(path + f.getName());
                zos.putNextEntry(ze);
                FileInputStream fis = new FileInputStream(f);
                byte[] buffer = new byte[1024];
                int size = -1;
                while ((size = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, size);
                }
            }
        }
    }
}
