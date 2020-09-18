package multithread;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9169);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String choice="0";
        do {
            System.out.println("-----WELCOME-----\n1.Login\t\t2.Sign Up\t\t911.Close the Socket");
            choice = keyboard.readLine();
            switch (choice) {
                case "1":
                    out.println(1);
                    System.out.print("Enter username: ");
                    out.println(keyboard.readLine());
                    System.out.print("Enter password: ");
                    out.println(keyboard.readLine());
                    System.out.println(reader.readLine());
                    break;
                case "2":
                    out.println(2);
                    System.out.print("Enter username: ");
                    out.println(keyboard.readLine());
                    System.out.print("Enter password: ");
                    out.println(keyboard.readLine());
                    System.out.println(reader.readLine());
                    break;
                case "911":
                    out.println(911);
                    keyboard.close();
                    reader.close();
                    out.close();
                    socket.close();
                    break;
                default:
                    System.out.println("Enter Valid Input");
            }
        }while (!choice.equals("911"));

    }
}
