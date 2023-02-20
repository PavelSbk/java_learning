package org.example.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * IO java предназначено для чтения и записи:
 * 1. из/в файл Files
 * 2. из/в сеть Network
 * 3. из/в память Memory
 * 4. из/в порты Ports - COM, USB
 * -
 * Типы потоков ввода/вывода:
 * Byte - Stream IO (1 bite)
 * Char - Reader Writer (2 bites)
 * Любой ввод/вывод - это поток байтов
 */

public class IO {

    /**
     * Copes file
     *
     * @param pathFrom - path where copy from
     * @param pathTo   - path where copy to
     */
    public static void copy(String pathFrom, String pathTo) {
        try (var in = new FileInputStream(pathFrom.toLowerCase());
             var out = new FileOutputStream(pathTo)) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copes file through buffer
     *
     * @param pathFrom - path where copy from
     * @param pathTo   - path where copy to
     */
    public static void bufferCopy(String pathFrom, String pathTo) {
        try (var in = new FileInputStream(pathFrom.toLowerCase());
             var out = new FileOutputStream(pathTo)) {
            byte[] buffer = new byte[4096]; // 4 KB / 4000 decimal Bytes / 4096 binary Bytes
            int r;
            while ((r = in.read(buffer)) != -1) {
                out.write(buffer, 0, r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String pathFrom = "data/Java IO.png";
        String pathTo = "data/testBufferCopyMtd.png";
        bufferCopy(pathFrom, pathTo);
    }
}

