package SDL_A3;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*; 
import java.net.*;

public class Cart {
	ArrayList <String> Cart = new ArrayList <String>();
	Scanner scan = new Scanner(System.in);
	
	public void add(Socket s) throws Exception {
		//PrintStream ps = new PrintStream(s.getOutputStream()); 
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		oos.writeObject("Enter an item you want:");
		String input = kb.readLine();
		Cart.add(input);
	}
	
	public void remove(Socket s) throws Exception {
		//PrintStream ps = new PrintStream(s.getOutputStream()); 
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter an item to remove:");
		String input = kb.readLine();
		if(Cart.contains(input)) {
			Cart.remove(input);
			oos.writeObject(input + " has been removed");
		}
		else
		{
			oos.writeObject(input + " was not found in your shopping cart");
		}
	}
	
	public void view(Socket s) throws IOException {
		//PrintStream ps = new PrintStream(s.getOutputStream());
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject("HELLLLOOOO");
		oos.writeObject(Cart);
	}
	
	public void clear() {
		Cart.clear();
	}
	
}

