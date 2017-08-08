import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class myServer  {
    //port out of bounds 1 - 1024
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                //blocks until there is a connection
                Socket socket = s.accept();
                new ServerOne(socket);
            }
        } finally {
            s.close();
        }

    }
}
