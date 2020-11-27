package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class P2pclient {
    public static void main(String[] args) throws SocketException, IOException {

        BufferedReader clientRead =new BufferedReader(new InputStreamReader(System.in));

        InetAddress IP = InetAddress.getByName("127.0.0.1");

        DatagramSocket clientSocket = new DatagramSocket();
        while(true)    //true
        {
            byte[] sendbuffer = new byte[1024];
            byte[] receivebuffer = new byte[1024];

            System.out.print("\n\nClient: ");
            String clientData = clientRead.readLine();
            sendbuffer = clientData.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendbuffer, sendbuffer.length, IP, 2604);
            clientSocket.send(sendPacket);
            if(clientData.equalsIgnoreCase("bye"))
            {
                System.out.println("\nConnection ended by client");
                break;
            }


            DatagramPacket receivePacket =
                    new DatagramPacket(receivebuffer, receivebuffer.length);
            clientSocket.receive(receivePacket);
            receivebuffer = receivePacket.getData();
            String serverData = new String(receivebuffer , StandardCharsets.UTF_8);

            System.out.print("\nServer: " + serverData);
            if(serverData.equals("bye"))
            {
                System.out.println("\n\nConnection ended by server...");
                break;
            }

        }
        clientSocket.close();
    }
}
