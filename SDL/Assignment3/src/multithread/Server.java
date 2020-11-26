package multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 9169;
    private static final ExecutorService pool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        String name, pass, url;
        Connection con;
        Class.forName("com.mysql.cj.jdbc.Driver");
        url = "jdbc:mysql://localhost:3306/moviedb";
        name = "root";
        pass = "mysql";
        con = DriverManager.getConnection(url, name, pass);
        ServerSocket listener = new ServerSocket(PORT);
        while(true){

            System.out.println("Waiting For Client");
            Socket client = listener.accept();
            System.out.println("Client Accepted");
            ClientHandler clientThread = new ClientHandler(client,con);

            pool.execute(clientThread);

        }
    }
}
