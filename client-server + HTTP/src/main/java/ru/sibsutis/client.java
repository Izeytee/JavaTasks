package ru.sibsutis;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws IOException {
        Socket cl = new Socket("127.0.0.1", 8081);
        Scanner in = new Scanner(cl.getInputStream());
        PrintWriter out = new PrintWriter(cl.getOutputStream());
        out.println("Hello world");
        out.flush();
        String str = in.nextLine();
        System.out.println(str);
        cl.close();
    }
}
