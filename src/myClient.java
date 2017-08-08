import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class myClient {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("DESKTOP-BBH4MLV");

        System.out.println("address = " + address);
        Socket socket = new Socket(address, myServer.PORT);

        try {
            System.out.println("socket = " + address);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            for (int i = 0; i < 10; i++) {
                out.println("howdy: " + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        }
        finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}
