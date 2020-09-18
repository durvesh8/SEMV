package multithread;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = in.readLine();
                if (request.equals("911")) break;
                if (request.equals("1")) {
                    String username = in.readLine();
                    String password = in.readLine();
                    out.println("Logged In!");
                }
                if (request.equals("2")) {
                    out.println("Sign Up:");
                    String username = in.readLine();
                    String password = in.readLine();
                    out.println("Signed Up");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
