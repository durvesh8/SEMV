//1
/*package SDL_A3;

import java.io.*; 
import java.net.*;

public class ClientHandler implements Runnable {
	
	private Socket client;
	private BufferedReader br;
	private PrintWriter ps;
	
	public ClientHandler(Socket clientSocket) throws IOException {
		this.client = clientSocket;
		br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		ps = new PrintWriter(client.getOutputStream());
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			int choice = br.read();
	        System.out.println("S: Client data is: " + choice);
	        
	        Product p = new Product();
			Cart c = new Cart();
			PersonalDetails pd = new PersonalDetails();
			//Server s = new Server();
			jdbc j = new jdbc();
	        
			System.out.println("Choice is: " + choice);
				
			switch(choice) {
				case 1:
					//p.set_product();
					//p.get_product();
						
					//JDBC
					j.connection();
					break;
				case 2:
					//c.add(client);
					//JDBC:
					j.jdbc_insert();
					break;
				case 3:
					//c.remove(client);
					//JDBC:
					j.jdbc_remove();
					break;
				case 4:
					//c.view(client);
					//JDBC:
					j.jdbc_view();
					break;
				case 5:
					c.clear();
					break;
				case 6:
					//pd.get_details();
					//JDBC:
					j.jdbc_getdetails();
					break;
				case 7:
					//pd.show_details();
					//JDBC:
					j.jdbc_showdetails();
					break;
				/*case 8:
					// close connection 
			        ps.close(); 
			        br.close(); 
			        kb.close(); 
			        ss.close(); 
			        client.close(); 
			        // terminate application 
			        System.exit(0); 
					//break;
				}
		}
		catch(IOException e) {
			System.err.println("IO Exception ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try { 
				br.close();
			}
			catch (IOException e) {
				System.err.println("IO Exception ");
			}
			ps.close();
		}
	}
}
*/

//2
package SDL_A3;

import java.io.*; 
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	
	private Socket client;
    private BufferedReader br;
    private PrintWriter ps;
    Connection con = null;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    Statement st;
	
	public ClientHandler(Socket clientSocket,Connection con) throws IOException, SQLException {
		this.client = clientSocket;
        this.con = con;
        st = con.createStatement();
        br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        ps = new PrintWriter(client.getOutputStream(), true);
		//BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		//Statement st = con.createStatement();
		System.out.println("INN");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//try {
			Scanner sc=new Scanner(System.in);
			//int choice = Integer.parseInt(br.readLine());
			int choice = 0;
			try {
				choice = br.read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("INNNNNNN");
		
			if(choice==2) {
				try {
				String s;
				ps.println("Enter item to add to the cart: ");
				s = br.readLine();
				//System.out.println(".." + s);
				st.executeUpdate("insert into cart values('"+s+"')");
			
				//st.executeUpdate("truncate table cart");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
				}
			
			if(choice==3) {
				try {
				String s;
				ps.println("Enter item to add to the cart: ");
				s = br.readLine();
				//System.out.println(".." + s);
				st.executeUpdate("delete from cart where item = "+s+"");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
			}
			
			if(choice==4) {
				try {
				ResultSet rs = st.executeQuery("select * from cart");
				
				while (rs.next()) {
					ps.println(rs.getString(1));
				}
				//st.executeUpdate("truncate table cart");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
			}
			
			if(choice==6) {
				try {
				String s1,s2,s3;
				ps.println("Enter name: ");
				s1 = br.readLine();
				ps.println("Enter address: ");
				s2 = br.readLine();
				ps.println("Enter phone no: ");
				s3 = br.readLine();
				//System.out.println(".." + s);
				st.executeUpdate("insert into personaldetails values('"+s1+"','"+s2+"','"+s3+"')");
				//st.executeUpdate("truncate table personaldetails");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
			}
			
			if(choice==7) {
				try {
				ResultSet rs = st.executeQuery("select * from cart");
				
				while (rs.next()) {
					ps.println(rs.getString(1));
				}
				//st.executeUpdate("truncate table personaldetails");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
		}
		
			if(choice==8) {
				try {
					br.close();
					ps.close();
				//st.executeUpdate("truncate table personaldetails");
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
		}
		
		/*finally {
			try { 
				br.close();
			}
			catch (IOException e) {
				System.err.println("IO Exception ");
			}
			ps.close();
		}*/
	}
}
