package ru.sibsutis;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Resp implements Runnable {

    private Socket socket;

    public Resp(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            String str = null;
            if (in.hasNextLine())
                str = in.nextLine();
            while (str != null && str.trim().length() != 0)
                str = in.nextLine();

            String tmp = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 30\r\n\r\n" +
                    "<html><body><h1>Hello</h1></body></html>";

            out.print(tmp);
            out.flush();

        } catch (Throwable exp) {

        } finally {
            try {
                socket.close();
            }
            catch (Throwable exp) {

            }
        }
    }
}
