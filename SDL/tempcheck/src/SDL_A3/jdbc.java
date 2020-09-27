package SDL_A3;

import java.sql.*;
import java.util.Scanner;

public class jdbc {
	Scanner sc=new Scanner(System.in);
	public void connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/db_grocery","root","Codingg@123");
			Statement st = con.createStatement();
			
			//st.executeUpdate("CREATE TABLE product(name varchar(20), price int)");
			st.executeUpdate("insert into product values('Milk',60)");
			st.executeUpdate("insert into product values('Butter',30)");
			st.executeUpdate("insert into product values('Dahi',40)");
			st.executeUpdate("insert into product values('Biscuits',20)");
			st.executeUpdate("insert into product values('Chocolate',10)");
			st.executeUpdate("insert into product values('Ice Cream',40)");
			st.executeUpdate("insert into product values('Snacks',20)");
			st.executeUpdate("insert into product values('Bread',35)");
			
			ResultSet rs = st.executeQuery("select * from product");
			
			while (rs.next()) {
				System.out.printf("%s %2d\n",rs.getString(1),rs.getInt(2));
			}
			st.executeUpdate("truncate table product");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void jdbc_insert() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/db_grocery","root","Codingg@123");
			Statement st = con.createStatement();
			
			//st.executeUpdate("CREATE TABLE cart(name varchar(20))");
			//st.executeUpdate("insert into product values('Milk',60)");
			
			String s;
			System.out.println("Enter item to add to the cart: ");
			s = sc.nextLine();
			//System.out.println(".." + s);
			st.executeUpdate("insert into cart values('"+s+"')");
			
			//String query = "insert into cart("'+
			
			//ResultSet rs = st.executeQuery("select * from cart");
			
			//while (rs.next()) {
				//System.out.printf("%s\n",rs.getString(1));
			//}
			//st.executeUpdate("truncate table cart");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void jdbc_remove() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/db_grocery","root","Codingg@123");
			Statement st = con.createStatement();
			
			//st.executeUpdate("CREATE TABLE cart(name varchar(20))");
			//st.executeUpdate("insert into product values('Milk',60)");
			
			String s;
			System.out.println("Enter item to add to the cart: ");
			s = sc.nextLine();
			//System.out.println(".." + s);
			st.executeUpdate("delete from cart where item = "+s+"");
			
			//String query = "insert into cart("'+
			
			//ResultSet rs = st.executeQuery("select * from cart");
			
			//while (rs.next()) {
			//	System.out.printf("%s\n",rs.getString(1));
			//}
			//st.executeUpdate("truncate table cart");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void jdbc_view() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/db_grocery","root","Codingg@123");
			Statement st = con.createStatement();
		
			ResultSet rs = st.executeQuery("select * from cart");
			
			while (rs.next()) {
				System.out.printf("%s\n",rs.getString(1));
			}
			st.executeUpdate("truncate table cart");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void jdbc_getdetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/db_grocery","root","Codingg@123");
			Statement st = con.createStatement();
			
			String s1,s2,s3;
			System.out.println("Enter name: ");
			s1 = sc.nextLine();
			System.out.println("Enter address: ");
			s2 = sc.nextLine();
			System.out.println("Enter phone no: ");
			s3 = sc.nextLine();
			//System.out.println(".." + s);
			st.executeUpdate("insert into personaldetails values('"+s1+"','"+s2+"','"+s3+"')");
			//st.executeUpdate("truncate table personaldetails");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void jdbc_showdetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =
					DriverManager.getConnection("jdbc:mysql://localhost:3306/db_grocery","root","Codingg@123");
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from cart");
			
			while (rs.next()) {
				System.out.printf("%s\n",rs.getString(1));
			}
			//st.executeUpdate("truncate table personaldetails");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

