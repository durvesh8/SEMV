//1
/*package SDL_A3;

import java.io.*; 
import java.net.*;
import java.util.Scanner; 
  
class Client { 
	
	
  
    //MAIN
    public static void main(String args[]) throws Exception { 
    	
    	System.out.println("C : In Client ");
        // Create client socket 
        Socket s = new Socket("localhost", 4442); 

        // to send data to the server 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

        // to read data coming from the server 
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); 

        // to read data from the keyboard 
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in)); 
  
        //while(true) {
        int cont;
        Scanner sc=new Scanner(System.in);
        //System.out.println("Continue? ");
        //cont = sc.nextInt();
         do {
        System.out.println("Enter choice: ");
        System.out.println(" 1. Product List \n 2. Add to Cart \n 3. Remove from Cart \n"
				+ " 4. View Cart \n 5. Empty Cart \n 6. Enter Personal Details \n "
				+ "7. Show Personal Details \n 8. Exit \n");
        int choice = Integer.parseInt(kb.readLine());
        dos.write(choice);
        
        System.out.println("S: Client data is: " + choice);
   
		
        System.out.println("\nContinue? ");
		cont= sc.nextInt(); 
        } while(cont == 1);
      //}
     // close connection 
        dos.close();
        br.close(); 
        kb.close(); 
        s.close(); 
    }
} 
*/
//2
package SDL_A3;

import java.io.*; 
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class Client { 
	
	
  
    //MAIN
    public static void main(String args[]) throws Exception { 
    	
    	System.out.println("C : In Client ");
        // Create client socket 
        Socket s = new Socket("localhost", 4442); 

        // to send data to the server 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

        // to read data coming from the server 
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); 

        // to read data from the keyboard 
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in)); 
  
        //while(true) {
        //int cont;
        //Scanner sc=new Scanner(System.in);
        //System.out.println("Continue? ");
        //cont = sc.nextInt();
        //do {
        Product p = new Product();
		Cart c = new Cart();
		PersonalDetails pd = new PersonalDetails();
		//Server s = new Server();
		jdbc j = new jdbc();
       
        System.out.println("Enter choice: ");
        System.out.println(" 1. Product List \n 2. Add to Cart \n 3. Remove from Cart \n"
				+ " 4. View Cart \n 5. Empty Cart \n 6. Enter Personal Details \n "
				+ "7. Show Personal Details \n 8. Exit \n");
       
//        Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection con =
//				DriverManager.getConnection("jdbc:mysql://localhost:3306/db_grocery","root","Codingg@123");
//		Statement st = con.createStatement();
//
        int choice = kb.read();
        while(choice != 9) {
        if(choice==1) {
			//p.set_product();
			//p.get_product();
				
			//JDBC
			j.connection();
        	}
        else if(choice==2) {
			dos.writeInt(choice);
        }
        else if(choice==3) {
			dos.writeInt(choice);
        }
        else if(choice==4) {
			dos.writeInt(choice);
        }
        else if(choice==5) {
			c.clear();
        }
        else if(choice==6) {
			dos.writeInt(choice);
        }
        else if(choice==7) {
			dos.writeInt(choice);
        }
        else if(choice==8) {
			dos.writeInt(choice);
        }	
		}
        
        //System.out.println("\nContinue? ");
		//cont= sc.nextInt(); 
        //} while(cont == 1);
        //}
     // close connection 
        //dos.close();
        //br.close(); 
        //kb.close(); 
        //s.close(); 
    }
} 
  
        /*public void CheckOutClient() throws Exception {
        	
        	System.out.println("C : In Client ");
            // Create client socket 
            Socket s = new Socket("localhost", 4443); 

            // to send data to the server 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

            // to read data coming from the server 
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); 

            // to read data from the keyboard 
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in)); 
        	
        	String str, str1; 
  
        	System.out.println("Check Out? Enter Yes/No "); 
        
        	str = kb.readLine(); 
        	System.out.println("Yes or No read");
        	dos.writeBytes(str + "\n"); 
  
        	// receive from the server 
        	str1 = br.readLine(); 
        
            System.out.println("C : Data sent from server: " + str1); 
  
            // close connection
            dos.close(); 
            br.close(); 
            kb.close(); 
            s.close(); 
    }*/ 


    
    /*
     *     	Product p = new Product();
		Cart c = new Cart();
		PersonalDetails pd = new PersonalDetails();
		jdbc j = new jdbc();
		
		Scanner sc=new Scanner(System.in);
		
		int choice,cont;
		String element;
		do {
			System.out.println("Enter choice: ");
			System.out.println(" 1. Product List \n 2. Add to Cart \n 3. Remove from Cart \n"
					+ " 4. View Cart \n 5. Empty Cart \n 6. Enter Personal Details \n "
					+ "7. Show Personal Details \n 8. Check Out/ Exit Shopping 	//Socket Programming \n"
					+ " 9. MultiThreading \n");
			
        	choice = kb.read(); 
			System.out.println("C : Data sent from server: " + choice); 

			// receive from the server 
			String str;
        	str = br.readLine(); 
        
            System.out.println("C : Data sent from server: " + str);
            
			switch(choice) {
				case 1:
					//p.set_product();
					//p.get_product();
					
					//JDBC : 
					j.connection();
					break;
				case 2:
					//c.add();
					
					//JDBC :
					j.insert();
					break;
				case 3:
					c.remove();
					break;
				case 4:
					c.view();
					break;
				case 5:
					c.clear();
					break;
				case 6:
					pd.get_details();
					break;
				case 7:
					pd.show_details();
					break;
				case 8:
					//cl.CheckOutClient();
					break;
				case 9:
					//s.CheckOutServer();
					break;
			}
			System.out.println("\nContinue? ");
			cont= sc.nextInt(); 
		}while(cont == 1);
		
		dos.close(); 
        br.close(); 
        kb.close(); 
        s.close(); 
	}
     */
