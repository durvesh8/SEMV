import java.io.*;
import java.net.*;
class Server
{
public static void main(String argv[]) throws Exception
{
String clientSentence;
String capitalizedSentence;

ServerSocket welcomeSocket = new ServerSocket(7006);
System.out.println("ServerSocket awaiting connections...");

while(true)
{
Socket connectionSocket = welcomeSocket.accept();
System.out.println("Connection from " + connectionSocket);

BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

clientSentence = inFromClient.readLine();
System.out.println(clientSentence);
capitalizedSentence = clientSentence.toUpperCase() + '\n';

outToClient.writeBytes(capitalizedSentence);

}
}
}