package multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static int PORT = 9169;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        while(true){
            System.out.println("Waiting For Client");
            Socket client = listener.accept();
            System.out.println("Client Accepted");
            ClientHandler clientThread = new ClientHandler(client);
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }
}
