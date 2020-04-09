package ru.sibsutis;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String []args) throws IOException {
        ServerSocket server = new ServerSocket(8081);
        while(true) {
            Socket client = server.accept();
            new Thread(new Resp(client)).start();
        }

	/*ServerSocket server = new ServerSocket(5000);
    	Socket client = server.accept();

    	Scanner in = new Scanner(client.getInputStream());
    	PrintWriter out = new PrintWriter(client.getOutputStream());

    	out.write("Hello");
    	out.flush();

	client.close();
        server.close();*/
    }
}