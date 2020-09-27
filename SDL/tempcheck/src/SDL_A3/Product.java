package SDL_A3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class Product {
	HashMap <String, Integer> Product = new HashMap <String, Integer>();
	
	public void set_product() {
		//System.out.println("C : In Client ");

		
		Product.put("Milk",60);
		Product.put("Butter",30);
		Product.put("Dahi",40);
		Product.put("Biscuits",20);
		Product.put("Chocolate",10);
		Product.put("Ice Cream",40);
		Product.put("Snacks",20);
		Product.put("Bread",35);
	}
	
	public void get_product() { 
		for (String i : Product.keySet()) {
			  System.out.println(i + "- " + Product.get(i));
			}
	}
	
}

