package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.net.*;
public class Multicastclient{
    public static void main(String args[]) {
        try{
            InetAddress group = InetAddress.getByName("225.4.5.6");
            MulticastSocket multiSocket = new MulticastSocket(2604) ;
            multiSocket.joinGroup(group);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while(true) {
                multiSocket.receive(packet);
                buffer = packet.getData();
                String data = new String(buffer , StandardCharsets.UTF_8);
                System.out.println("Server > "+ data);
                if(data.equals("exit")){
                    System.out.println("\nServer connection terminated...");
                    break;
                }
            }
            multiSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}