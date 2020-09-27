package SDL_A3;

import java.io.*; 
import java.net.*;
import java.util.ArrayList; 
  
class ServerMulti { 
  
	public ArrayList <ClientHandler> clients = new ArrayList<>();
	
    //public static void CheckOutServer() throws Exception { 
	 public static void main(String args[]) throws Exception {
  
		 Multithread object = new Multithread(); 
         object.start();
		 
    	System.out.println("S : Server started ");
        // Create server Socket 
        ServerSocket ss = new ServerSocket(4446); 
  
        System.out.println("S : Server waiting.. ");
        // connect it to client socket 
        Socket s = ss.accept(); 
        System.out.println("S : Connection established "); 
  
        // to send data to the client 
        PrintStream ps = new PrintStream(s.getOutputStream()); 
  
        // to read data coming from the client 
        BufferedReader br  = new BufferedReader(new InputStreamReader(s.getInputStream())); 
  
        // to read data from the keyboard 
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in)); 
        
        //DataInputStream din = new DataInputStream(s.getInputStream());
  
        String str, str1 = ""; 
        
        str = br.readLine();
        //str = din.readUTF();
        System.out.println("S : Client data: "+ str);
            
        if(str.equals("Yes"))
            str1 = "Check Out Successful!:D ";
        else if (str.equals("No"))
           	str1 = "Check Out Cancelled. Your bag seems heavy, give us a try! "; 
        else
         	str1 = "Enter valid option. ";
        
     	ps.println(str1);
  
        System.out.println("S : Data sent ");
        
        // close connection 
        ps.close(); 
        br.close(); 
        kb.close(); 
        ss.close(); 
        s.close(); 
  
        // terminate application 
        System.exit(0); 
    } 
}
