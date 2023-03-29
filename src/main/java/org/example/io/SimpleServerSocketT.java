package org.example.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServerSocketT {

    public static void main(String[] args) throws IOException {
        int port = 19999;
        System.out.println("Try to bind to port: " + port);
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server socked is opened");
        Socket socket = server.accept();
        System.out.println("Connection is accepted");

        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
            if (str.equalsIgnoreCase("bye")) {
                pw.println("Bye");
                break;
            } else {
                pw.println("Server answers: " + str);
            }
        }
        pw.close();
        br.close();
        server.close();
    }
}
