import java.io.*;
import java.net.Socket;

public class ServerOne extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerOne(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        start(); //call run method
    }

    @Override
    public void run() {
        try {
            while (true) {
                String string = in.readLine();
                if (string.equals("END"))
                    break;
                System.out.println("Echoing: " + string);
                out.println(string);
            }
            System.out.println("closing...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception");
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}
