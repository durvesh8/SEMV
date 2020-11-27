package udp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
public class Multicastserver{
    public static void main(String args[]) {
        try {
            InetAddress group = InetAddress.getByName("225.4.5.6");
            MulticastSocket multiSocket= new MulticastSocket(2604) ;
            multiSocket.joinGroup(group);
            String data = "";
            BufferedReader ip =new BufferedReader(new InputStreamReader(System.in));
            while(!data.equals("exit")) {
                System.out.println("Server > ");
                data = ip.readLine();
                DatagramPacket sendPacket = new
                        DatagramPacket(data.getBytes(),data.length(),group,2604);
                multiSocket.send(sendPacket);
            }
            multiSocket.close();
            System.out.println("\nSocket Closed...");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
