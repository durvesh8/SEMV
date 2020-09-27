package SDL_A3;

import java.io.*; 
import java.net.*;

class Multithread extends Thread { 
	
	private Socket client;
	private BufferedReader br;
	private PrintWriter ps;
	
    public void run() { 
        try { 
            // Displaying the thread that is running 
        	
        	br = new BufferedReader(new InputStreamReader(client.getInputStream()));
    		ps = new PrintWriter(client.getOutputStream());
    		
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
        	
            System.out.println ("Thread " + 
                  Thread.currentThread().getId() + 
                  " is running"); 
  
        } 
        catch (Exception e) { 
            // Throwing an exception
            System.out.println ("Exception is caught"); 
        } 
    } 
} 