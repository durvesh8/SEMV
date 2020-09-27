/*package SDL_A3;

import java.util.Scanner;

public class Menu {
	public static void main(String[] args) throws Exception {
		Product p = new Product();
		Cart c = new Cart();
		PersonalDetails pd = new PersonalDetails();
		Server s = new Server();
		jdbc j = new jdbc();
		Client cl = new Client();
		//ClientHandler ch = new ClientHandler();
		//MultiThread m = new MultiThread();
		
		Scanner sc=new Scanner(System.in);
		
		int choice,cont;
		String element;
		do {
			System.out.println("Enter choice: ");
			System.out.println(" 1. Product List \n 2. Add to Cart \n 3. Remove from Cart \n"
					+ " 4. View Cart \n 5. Empty Cart \n 6. Enter Personal Details \n "
					+ "7. Show Personal Details \n 8. Check Out/ Exit Shopping 	//Socket Programming \n"
					+ "9. MultiThreading \n");
			choice= sc.nextInt();
			sc.nextLine();

			switch(choice) {
				case 1:
					//p.set_product();
					//p.get_product();
					
					//JDBC
					j.connection();
					break;
				case 2:
					//c.add();
					
					//JDBC
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
					s.CheckOutServer();
					break;
				case 9:
					s.CheckOutServer();
					break;
			}
			System.out.println("\nContinue? ");
			cont= sc.nextInt(); 
		}while(cont == 1);
	}
}*/
