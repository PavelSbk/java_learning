package org.example.io;

import java.io.*;

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

    /**
     * Write text in to the file
     *
     * @param pathTo - path where write to
     */
    public static void textWriter(String text, String pathTo) {
        try (var out = new FileOutputStream(pathTo)) {
            out.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read text
     *
     * @param pathFrom - path where read from
     */
    public static void textReader(String pathFrom) {
        try (var reader = new FileReader(pathFrom)) {
            var text = new StringBuffer();
            int c;
            while ((c = reader.read()) != -1) {
                text.append((char) c);
            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return text from file
     * @param pathFrom - path where read from
     * @return StringBuffer
     */
    public static String textToString(String pathFrom) {
        try (var reader = new FileReader(pathFrom)) {
            var text = new StringBuffer();
            int c;
            while ((c = reader.read()) != -1) {
                text.append((char) c);
            }
            return text.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert byte input stream in to the stream of char UTF-8
     *
     * @param pathFrom - path where read from
     */
    public static void encoder(String path) {
        try (var input = new FileInputStream(path);
             Reader ir = new InputStreamReader(input, "utf-8")) { // encoder
            BufferedReader br = new BufferedReader(ir);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String rubai = "Чтоб мудро жизнь прожить, знать надобно немало,\n" +
                "Два важных правила запомни для начала:\n" +
                "Ты лучше голодай, чем что попало есть,\n" +
                "И лучше будь один, чем вместе с кем попало.";
        String pathFrom = "data/testWriter.txt";

    }
}

