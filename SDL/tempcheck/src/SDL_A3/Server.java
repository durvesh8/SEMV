package SDL_A3;

import java.io.*; 
import java.net.*;
import java.sql.DriverManager;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.Connection;

//import com.mysql.cj.xdevapi.Statement;

 
  
class Server { 
	
	private static ArrayList<ClientHandler> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newCachedThreadPool();
  
    //public static void CheckOutServer() throws Exception { 
	 public static void main(String args[]) throws Exception {
		 
    	System.out.println("S : Server started ");
        // Create server Socket 
    	String name, pass, url;
        Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        url = "jdbc:mysql://localhost:3306/tempeeesha";
        name = "root";
        pass = "mysql";
        con = DriverManager.getConnection(url, name, pass);
        ServerSocket ss = new ServerSocket(4442); 
        
        while(true) {
        	System.out.println("S: Server waiting ");
            Socket client = ss.accept();
            System.out.println("S: Connection Established! ");
            ClientHandler clientThread = new ClientHandler(client,con);
            clients.add(clientThread);

            pool.execute(clientThread);
            System.out.println("S : ............");
        }
     }
}

	
	
	/*public static void CheckOutServer() throws Exception { 
		
		System.out.println("S : Server started ");
        // Create server Socket 
        ServerSocket ss = new ServerSocket(4443); 
  
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
        
        //..
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
    }*/ 

