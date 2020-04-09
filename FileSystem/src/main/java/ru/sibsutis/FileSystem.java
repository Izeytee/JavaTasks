package ru.sibsutis;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;
import java.util.Date;
import java.util.Scanner;

public class FileSystem {
    public static void main(String []argc) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path log = Path.of("log.txt");
        Path tmp = Path.of("."); //choose direcotory
        Files.write(log, "".getBytes());
        WatchKey key = tmp.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);

        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                Files.write(log, (event.context() + " ").getBytes(), StandardOpenOption.APPEND);
                Files.write(log, (event.kind().name() + " ").getBytes(), StandardOpenOption.APPEND);
                Files.write(log, (new Date() + "\n").getBytes(), StandardOpenOption.APPEND);
            }
            key.reset();
        }

        ServerSocket server = new ServerSocket(8081);
        while(true) {
            Socket client = server.accept();
            new Thread(new Resp(client)).start();
        }
    }
}

class Resp implements Runnable {

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

            String allFiles = new String("<html><body>");
            for (File file : new File(".").listFiles())
                allFiles += file.getName() + "<br>";
            allFiles += "</body></html>";

            String tmp = "HTTP/1.1 200 OK\r\n" +
                    "Server: YarServer/2020-03-04\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + allFiles.length()  + "\r\n" +
                    "Connection: close\r\n\r\n" +
                    allFiles;

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
