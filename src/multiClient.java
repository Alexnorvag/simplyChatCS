import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static int counter = 0;
    private int id = counter++;
    private static int threadcount = 0;

    public static int getThreadcount() {
        return threadcount;
    }

    public ClientThread(InetAddress address) {
        System.out.println("Making client " + id);
        threadcount++;
        try {
            socket = new Socket(address, myServer.PORT);
        } catch (IOException e) {
            System.err.println("Socked failed");
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            start();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                System.err.println("Socket not closed");
            }
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 25; i++) {
                out.println("Client " + id + ": " + i);
                String string = in.readLine();
                System.out.println(string);
            }
            out.println("END");
        } catch (IOException e) {
            System.err.println("IOException");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
            threadcount--;
        }
    }
}

public class multiClient {
    static final int MAX_THREADS = 40;

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        InetAddress address = InetAddress.getByName("DESKTOP-BBH4MLV");
        while (true) {
            if (ClientThread.getThreadcount() < MAX_THREADS)
                new ClientThread(address);
            Thread.currentThread().sleep(100);
        }
    }
}
