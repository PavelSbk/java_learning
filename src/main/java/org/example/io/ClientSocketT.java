package org.example.io;

import java.io.*;
import java.net.Socket;

public class ClientSocketT {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("java-course.ru", 80);
        socket.setSoTimeout(2000);
        /** Команды:
         * GET /haiku.html HTTP/1.1
         * Host: java-course.ru
         * Connection: close
         * \r\n\r\n
         */
        StringBuilder sb = new StringBuilder("GET /haiku.html HTTP/1.1");
        sb.append(System.lineSeparator());
        sb.append("Host: java-course.ru").append(System.lineSeparator());
        sb.append("Connection: close").append(System.lineSeparator());
        sb.append(System.lineSeparator());
        /**
         * OutputStream os - стрим для записи данных полученных из socket
         * getOutputStream() - получает данные
         */
        OutputStream os = socket.getOutputStream();
        os.write(sb.toString().getBytes());
        /**
         * InputStreamReader(in, "cp1251") - преобразует байтовый поток в символьный (charset)
         */
        InputStream in = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "cp1251"));
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            try {
                line = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
        socket.close();
    }
}
