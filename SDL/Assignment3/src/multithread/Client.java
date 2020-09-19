package multithread;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9169);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String choice = "0";
        do {
            System.out.println("-----WELCOME-----\n1.Login\t\t2.Sign Up\t\t911.Close the Socket");
            choice = keyboard.readLine();
            switch (choice) {
                case "1":
                    out.println(1);
                    System.out.print("Enter username: ");
                    out.println(keyboard.readLine());
                    System.out.print("Enter password: ");
                    out.println(keyboard.readLine());
                    String usernameresponse = reader.readLine();
                    if (usernameresponse.equals("0"))
                        System.out.println("Account with this username doesn't exist!");
                    else {
                        String passwordresponse = reader.readLine();
                        if (passwordresponse.equals("0"))
                            System.out.println("Incorrect Password");
                        else if (passwordresponse.equals("420")) {
                            System.out.println("You're logged in as admin.");
                            String adminresp = "";
                            while (!adminresp.equals("4")) {
                                System.out.println("Choose what to do:-\n1.Add a movie\t2.Remove a movie\t3.Remove a user\t4.Log out");
                                adminresp = keyboard.readLine();
                                switch (adminresp) {
                                    case "1":
                                        out.println("1");
                                        System.out.print("Enter the movie name: ");
                                        out.println(keyboard.readLine());
                                        System.out.print("Enter the genre: ");
                                        out.println(keyboard.readLine());
                                        String servresp = "";
                                        servresp = reader.readLine();
                                        if (servresp.equals("0")) {
                                            System.out.println("---Movie Already Exists---");
                                        } else if(servresp.equals("1")) {
                                            System.out.println("---Movie Added---");
                                        }
                                        break;
                                    case "2":
                                        out.println("2");
                                        System.out.print("Enter the name of the movie to be deleted: ");
                                        out.println(keyboard.readLine());
                                        String serv = reader.readLine();
                                        if (serv.equals("0")) {
                                            System.out.println("---Movie Doesn't exist---");
                                        } else {
                                            System.out.println("---Movie Deleted---");
                                        }
                                        break;
                                    case "3":
                                        out.println("3");
                                        System.out.print("Enter the username of the user to be deleted: ");
                                        out.println(keyboard.readLine());
                                        String srvrusr = reader.readLine();
                                        if (srvrusr.equals("0")) System.out.println("----User Doesn't Exist----");
                                        else System.out.println("----User Deleted----");
                                        break;
                                    case "4":
                                        break;
                                }
                            }
                        } else {
                            System.out.println("Logged In Successfully");
                            String loginchoice;
                            label:
                            while (true) {
                                System.out.println("\n1. A new movie recommendation.\t\t2. A rewatch recommendation\t\t3.Sign out and exit");
                                loginchoice = keyboard.readLine();
                                switch (loginchoice) {
                                    case "3":
                                        out.println("3");
                                        choice = "911";
                                        break label;
                                    case "2":
                                        out.println("2");
                                        String rewatch = "";
                                        String rewatchchoice = "";
                                        while (true) {
                                            rewatch = reader.readLine();
                                            if (rewatch.equals("404")) {
                                                System.out.println("We're out of movies :(");
                                                break;
                                            }
                                            System.out.print("\nThe rewatch recommendation is: ");
                                            System.out.println(rewatch);
                                            System.out.print("Would you like a different recommendation?(y/n)   ");
                                            rewatchchoice = keyboard.readLine();
                                            if (rewatchchoice.equals("y")) out.println("0");
                                            else {
                                                out.println("1");
                                                break;
                                            }
                                        }
                                        break;
                                    case "1":
                                        out.println("1");
                                        String recomm = "";
                                        String recommchoice = "";
                                        while (true) {
                                            recomm = reader.readLine();
                                            if (recomm.equals("404")) {
                                                System.out.println("We're out of movies :(");
                                                break;
                                            }
                                            System.out.print("\nThe movie recommendation is: ");
                                            System.out.println(recomm);
                                            System.out.print("Would you like a different recommendation?(y/n)   ");
                                            recommchoice = keyboard.readLine();
                                            if (recommchoice.equals("y")) out.println("0");
                                            else {
                                                out.println("1");
                                                System.out.println("We hope you enjoy the movie!!!!");
                                                break;
                                            }
                                        }
                                        break;
                                }
                            }
                        }

                    }
                    break;
                case "2":
                    out.println(2);
                    System.out.print("Enter username: ");
                    out.println(keyboard.readLine());
                    System.out.print("Enter password: ");
                    out.println(keyboard.readLine());
                    String serverresp = reader.readLine();
                    if (serverresp.equals("0")) {
                        System.out.println("Username Already Exists!");
                    } else {
                        System.out.println("Account Created Successfully!!");

                    }
                    break;
                case "911":
                    out.println(911);
                    keyboard.close();
                    reader.close();
                    out.close();
                    socket.close();
                    break;
                default:
                    System.out.println("Enter Valid Input");
            }
        } while (!choice.equals("911"));
        out.println(911);
        keyboard.close();
        reader.close();
        out.close();
        socket.close();

    }
}
