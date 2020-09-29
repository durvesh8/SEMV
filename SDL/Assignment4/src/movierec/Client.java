package movierec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {
    JFrame homescreen;
    JLabel label1;
    JPanel panel;
    JButton signUp,login,exit;
    JLabel backg,suerrormessage;
    JTextArea suusernamefield;
    JPasswordField supasswordfield;
    ActionListener actionListenersignup;
    BufferedReader keyboard;
    BufferedReader reader;
    PrintWriter out;
    JFrame signuppage;

    public Client() {
        try {
            Socket socket = new Socket("127.0.0.1", 9169);
            homescreen = new JFrame();
            homescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homescreen.setResizable(true);
            homescreen.setPreferredSize(new Dimension(1540, 825));
            homescreen.setTitle("Movie recommender");
//        backg = new JLabel(new ImageIcon("D:\\SEMV\\SDL\\Assignment4\\assets\\moviegrid.jpg"));
//        backg.setBounds(0,0,1920,1080);
            panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.DARK_GRAY);
            signUp = new JButton("Sign Up");
            signUp.setBounds(865, 540, 100, 100);
            signUp.setBackground(Color.orange);
            login = new JButton("Login");
            login.setBounds(565, 540, 100, 100);
            login.setBackground(Color.orange);
            label1 = new JLabel("Movie Recommendations");
            label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
            label1.setBounds(490, 150, 650, 50);
            label1.setForeground(Color.lightGray);
            exit = new JButton("Exit");
            exit.setBounds(1400, 700, 60, 60);
            exit.setBackground(Color.pink);
//        panel.add(backg);
            panel.add(login);
            panel.add(signUp);
            panel.add(exit);
            panel.add(label1);
            homescreen.add(panel);
            homescreen.pack();
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Making it fullscreen
//        frame.setUndecorated(true);
//        frame.setLocationRelativeTo(null);
            homescreen.setVisible(true);
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            signUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    out.println(2);
                    homescreen.dispose();
                    signuppage = new JFrame();
                    signuppage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    signuppage.setResizable(true);
                    signuppage.setPreferredSize(new Dimension(1540, 825));
                    signuppage.setTitle("Sign Up");
                    JPanel signuppanel = new JPanel();
                    signuppanel.setLayout(null);
                    signuppanel.setBackground(Color.DARK_GRAY);
                    JButton signupbutton = new JButton("Sign Up");
                    signupbutton.setBounds(720, 600, 100, 50);
                    signupbutton.setBackground(Color.orange);
                    JLabel suusername = new JLabel("Username:");
                    suusername.setFont(new Font("username", Font.BOLD, 15));
                    suusername.setBounds(600, 300, 100, 15);
                    suusername.setForeground(Color.lightGray);
                    JLabel supassword = new JLabel("Password:");
                    supassword.setFont(new Font("password", Font.BOLD, 15));
                    supassword.setBounds(600, 400, 100, 15);
                    supassword.setForeground(Color.lightGray);
                    JLabel sign_up = new JLabel("Enter Details to register");
                    sign_up.setFont(new Font("signup", Font.PLAIN, 50));
                    sign_up.setBounds(515, 150, 650, 60);
                    sign_up.setForeground(Color.lightGray);
                    suerrormessage = new JLabel(":)");
                    suerrormessage.setForeground(Color.red);
                    suerrormessage.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
                    suerrormessage.setBounds(765, 200, 100, 30);
                    suusernamefield = new JTextArea();
                    supasswordfield = new JPasswordField();
                    suusernamefield.setBounds(700, 300, 200, 20);
                    supasswordfield.setBounds(700, 400, 200, 20);
                    signupbutton.addActionListener(actionListenersignup);

                    signuppanel.add(sign_up);
                    signuppanel.add(signupbutton);
                    signuppanel.add(suerrormessage);
                    signuppanel.add(suusername);
                    signuppanel.add(supassword);
                    signuppanel.add(supasswordfield);
                    signuppanel.add(suusernamefield);
                    signuppage.add(signuppanel);
                    signuppage.pack();
                    signuppage.setVisible(true);
                }
            });
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        new Client();
//        String choice;
//        do {
//
//            System.out.println("-----WELCOME-----\n1.Login\t\t2.Sign Up\t\t911.Close the Socket");
//            choice = keyboard.readLine();
//            label:
//            switch (choice) {
//                case "1":
//                    out.println(1);
//                    System.out.print("Enter username: ");
//                    out.println(keyboard.readLine());
//                    System.out.print("Enter password: ");
//                    out.println(keyboard.readLine());
//                    String usernameresponse = reader.readLine();
//                    if (usernameresponse.equals("0"))
//                        System.out.println("Account with this username doesn't exist!");
//                    else {
//                        String passwordresponse = reader.readLine();
//                        if (passwordresponse.equals("0"))
//                            System.out.println("Incorrect Password");
//                        else {
//                            System.out.println("Logged In Successfully");
//                            String loginchoice;
//                            while (true) {
//                                System.out.println("\n1. A new movie recommendation.\t\t2. A rewatch recommendation\t\t3.Sign out and exit");
//                                loginchoice = keyboard.readLine();
//                                switch (loginchoice) {
//                                    case "3":
//                                        System.out.println("Are you sure?(y/n)\t\t\tP.S. Enter x to delete your account.");
//                                        String confirm = keyboard.readLine();
//                                        if (confirm.equals("y")) {
//                                            out.println("3");
//                                            choice = "911";
//                                            break label;
//                                        }
//                                        if (confirm.equals("n")) {
//                                            out.println("4");
//                                            continue;
//                                        }
//                                        if (confirm.equals("x")) {
//                                            out.println("x");
//                                            System.out.println("Account Deleted Successfully");
//                                            choice = "911";
//                                            break label;
//                                        }
//                                    case "2":
//                                        out.println("2");
//                                        String rewatch;
//                                        String rewatchchoice;
//                                        while (true) {
//                                            rewatch = reader.readLine();
//                                            if (rewatch.equals("404")) {
//                                                System.out.println("We're out of movies :(");
//                                                break;
//                                            }
//                                            System.out.print("\nThe rewatch recommendation is: ");
//                                            System.out.println(rewatch);
//                                            System.out.print("Would you like a different recommendation?(y/n)   ");
//                                            rewatchchoice = keyboard.readLine();
//                                            if (rewatchchoice.equals("y")) out.println("0");
//                                            else {
//                                                out.println("1");
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    case "1":
//                                        out.println("1");
//                                        String recomm;
//                                        String recommchoice;
//                                        while (true) {
//                                            recomm = reader.readLine();
//                                            if (recomm.equals("404")) {
//                                                System.out.println("We're out of movies :(");
//                                                break;
//                                            }
//                                            System.out.print("\nThe movie recommendation is: ");
//                                            System.out.println(recomm);
//                                            System.out.print("Would you like a different recommendation?(y/n)   ");
//                                            recommchoice = keyboard.readLine();
//                                            if (recommchoice.equals("y")) out.println("0");
//                                            else {
//                                                out.println("1");
//                                                System.out.println("We hope you enjoy the movie!!!!");
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                }
//                            }
//                        }
//
//                    }
//                    break;
//                case "2":
//                    out.println(2);
//                    System.out.print("Enter username: ");
//                    out.println(keyboard.readLine());
//                    System.out.print("Enter password: ");
//                    out.println(keyboard.readLine());
//                    String serverresp = reader.readLine();
//                    if (serverresp.equals("0")) {
//                        System.out.println("Username Already Exists!");
//                    } else {
//                        System.out.println("Account Created Successfully!!");
//
//                    }
//                    break;
//                case "911":
//                    out.println(911);
//                    keyboard.close();
//                    reader.close();
//                    out.close();
//                    socket.close();
//                    break;
//                default:
//                    System.out.println("Enter Valid Input");
//            }
//        } while (!choice.equals("911"));
//        out.println(911);
//        keyboard.close();
//        reader.close();
//        out.close();
//        socket.close();

    }

    public void signup() {
        actionListenersignup = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(true) {
                    String username = suusernamefield.getText();
                    String password = supasswordfield.getText();
                    out.println(username);
                    out.println(password);

                    try {
                        String serverresp = reader.readLine();
                        if (serverresp.equals("0")) {
                            suerrormessage.setText("Username Already Exists!");
                        } else {
                            suerrormessage.setFont(new Font("successfull", Font.BOLD, 15));
                            suerrormessage.setText("Account Created Successfully!!");
                            signuppage.setVisible(false);
                            break;
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        };
    }
}
