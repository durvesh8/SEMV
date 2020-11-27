package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;


public class P2pserver {


    public static void main(String[] args) throws SocketException, IOException {

        DatagramSocket serverSocket = new DatagramSocket(2604);

        while(true)
        {
            byte[] receivebuffer = new byte[1024];
            byte[] sendbuffer  = new byte[1024];
            DatagramPacket recvdpkt = new DatagramPacket(receivebuffer, receivebuffer.length);
            serverSocket.receive(recvdpkt);
            receivebuffer = recvdpkt.getData();
            InetAddress IP = recvdpkt.getAddress();
            int portno = recvdpkt.getPort();
            String clientdata = new String(receivebuffer , StandardCharsets.UTF_8);
            if(clientdata.equals("bye"))
            {
                System.out.println("\n\nConnection ended by client...");
                break;
            }
            System.out.println("\nClient : "+ clientdata);
            System.out.print("\nServer : ");
            BufferedReader serverRead = new BufferedReader(new InputStreamReader (System.in) );
            String serverdata = serverRead.readLine();

            sendbuffer = serverdata.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP,portno);
            serverSocket.send(sendPacket);

            if(serverdata.equalsIgnoreCase("bye"))
            {
                System.out.println("\nConnection ended by server...");
                break;
            }


        }
        serverSocket.close();
    }

}
