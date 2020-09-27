package SDL_A3;

import java.util.Scanner;
import java.util.Stack;

public class PersonalDetails {
	Stack <String> Details = new Stack <String>();
	Stack <String> Details2 = new Stack <String>();
	Scanner scan = new Scanner(System.in);
	
	public void get_details() {
		System.out.println("Enter your Name:");
		String name = scan.nextLine();
		Details.push(name);
		System.out.println("Enter your Shipping Address:");
		String address = scan.nextLine();
		Details.push(address);
	}
	
	public void show_details() {
		Details2.push(Details.pop());
		System.out.println("Name: " + Details.pop());
		System.out.println("Shipping Address: " + Details2.pop());
	}
	
}
