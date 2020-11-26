import java.io.*;
import java.net.*;
class Client
{
public static void main(String argv[]) throws Exception
{
String userchoice;
String username;
String password;

Socket clientSocket = new Socket("localhost", 7006);
System.out.println("Connected to Server. Enter any message:");

BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
System.out.println("1.Log In\t2.Sign Up\t3. Exit");
userchoice = inFromUser.readLine();

DataOutputStream outToServer =new DataOutputStream(clientSocket.getOutputStream());

BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

if(userchoice.equals("3")){
    clientSocket.close();
}
else if(userchoice.equals("1")){
outToServer.writeBytes(userchoice + '\n');
System.out.println("Log in bruh");
// modifiedSentence = inFromServer.readLine();

// System.out.println("FROM SERVER: " +modifiedSentence);

clientSocket.close();
}
}
}