package movierec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class frames {
    static JFrame homescreen, recframe, loginframe, signuppage;
    static JButton login, signUp, exit, newrecbutton, rewatchbutton, signout, deleteacc, loginbutton, signupbutton;
    static JLabel newrecommendation, rewatchrecommendation, lierrormessage, suerrormessage;
    static JTextArea suusernamefield, liusernamefield;
    static JPasswordField supasswordfield, lipasswordfield;

    public frames() {
        setuphomescreenframe();
        setupsignupframe();
        setuploginframe();
        setuprecommendationframe();
    }

    public static JButton getLogin() {
        return login;
    }

    public static JButton getExit() {
        return exit;
    }

    public static JButton getSignUp() {
        return signUp;
    }

    public static void setuphomescreenframe() {
        homescreen = new JFrame();
        homescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homescreen.setResizable(true);
        homescreen.setPreferredSize(new Dimension(1540, 825));
        homescreen.setTitle("Movie recommender");
//        backg = new JLabel(new ImageIcon("D:\\SEMV\\SDL\\Assignment4\\assets\\moviegrid.jpg"));
//        backg.setBounds(0,0,1920,1080);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        signUp = new JButton("Sign Up");
        signUp.setBounds(865, 540, 100, 100);
        signUp.setBackground(Color.orange);
        login = new JButton("Login");
        login.setBounds(565, 540, 100, 100);
        login.setBackground(Color.orange);
        JLabel label1 = new JLabel("Movie Recommendations");
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
    }

    public static void setuprecommendationframe() {
        recframe = new JFrame();
        recframe.setPreferredSize(new Dimension(1540, 825));
        recframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        recframe.setResizable(true);
        recframe.setTitle("LoginPage");
        JPanel recpanel = new JPanel();
        recpanel.setLayout(null);
        recpanel.setBackground(Color.DARK_GRAY);
        JLabel title = new JLabel("Click on the desired button");
        title.setBounds(600, 50, 900, 50);
        title.setFont(new Font("title", Font.ITALIC, 30));
        title.setForeground(Color.lightGray);
        newrecbutton = new JButton("New Recommendation");
        newrecbutton.setBounds(300, 200, 200, 50);
        newrecbutton.setBackground(Color.orange);
        newrecommendation = new JLabel("");
        newrecommendation.setFont(new Font("new reco.", Font.BOLD, 15));
        newrecommendation.setBounds(600, 200, 700, 20);
        newrecommendation.setForeground(Color.lightGray);
        rewatchbutton = new JButton("Rewatch recommendation");
        rewatchbutton.setBounds(300, 300, 200, 50);
        rewatchbutton.setBackground(Color.orange);
        rewatchrecommendation = new JLabel("");
        rewatchrecommendation.setFont(new Font("rewatch reco.", Font.BOLD, 15));
        rewatchrecommendation.setBounds(600, 300, 700, 15);
        rewatchrecommendation.setForeground(Color.lightGray);
        signout = new JButton("Sign Out");
        signout.setBounds(1300, 10, 200, 50);
        signout.setBackground(Color.WHITE);
        deleteacc = new JButton("Delete Account");
        deleteacc.setBounds(1300, 600, 200, 50);
        deleteacc.setBackground(Color.RED);
        recpanel.add(title);
        recpanel.add(newrecbutton);
        recpanel.add(signout);
        recpanel.add(rewatchrecommendation);
        recpanel.add(rewatchbutton);
        recpanel.add(newrecommendation);
        recpanel.add(deleteacc);
        recframe.add(recpanel);
        recframe.pack();
    }

    public static void setuploginframe() {
        loginframe = new JFrame();
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.setResizable(true);
        loginframe.setPreferredSize(new Dimension(1540, 825));
        loginframe.setTitle("LoginPage");
        JPanel loginpanel = new JPanel();
        loginpanel.setLayout(null);
        loginpanel.setBackground(Color.DARK_GRAY);
        loginbutton = new JButton("Login");
        loginbutton.setBounds(720, 600, 100, 50);
        loginbutton.setBackground(Color.orange);
        JLabel liusername = new JLabel("Username:");
        liusername.setFont(new Font("username", Font.BOLD, 15));
        liusername.setBounds(600, 300, 100, 15);
        liusername.setForeground(Color.lightGray);
        JLabel lipassword = new JLabel("Password:");
        lipassword.setFont(new Font("password", Font.BOLD, 15));
        lipassword.setBounds(600, 400, 100, 15);
        lipassword.setForeground(Color.lightGray);
        JLabel log_in = new JLabel("Enter your credentials");
        log_in.setFont(new Font("login", Font.PLAIN, 50));
        log_in.setBounds(515, 150, 650, 60);
        log_in.setForeground(Color.lightGray);
        lierrormessage = new JLabel("");
        lierrormessage.setForeground(Color.red);
        lierrormessage.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        lierrormessage.setBounds(700, 200, 300, 30);
        liusernamefield = new JTextArea();
        lipasswordfield = new JPasswordField();
        liusernamefield.setBounds(700, 300, 200, 20);
        lipasswordfield.setBounds(700, 400, 200, 20);


        loginpanel.add(log_in);
        loginpanel.add(loginbutton);
        loginpanel.add(lierrormessage);
        loginpanel.add(liusername);
        loginpanel.add(lipassword);
        loginpanel.add(lipasswordfield);
        loginpanel.add(liusernamefield);
        loginframe.add(loginpanel);
        loginframe.pack();


    }

    public static void setupsignupframe() {
        signuppage = new JFrame();
        signuppage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signuppage.setResizable(true);
        signuppage.setPreferredSize(new Dimension(1540, 825));
        signuppage.setTitle("Sign Up");
        JPanel signuppanel = new JPanel();
        signuppanel.setLayout(null);
        signuppanel.setBackground(Color.DARK_GRAY);
        signupbutton = new JButton("Sign Up");
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
        suerrormessage = new JLabel("");
        suerrormessage.setForeground(Color.red);
        suerrormessage.setFont(new Font("Error Message", Font.PLAIN, 15));
        suerrormessage.setBounds(700, 200, 300, 30);
        suusernamefield = new JTextArea();
        supasswordfield = new JPasswordField();
        suusernamefield.setBounds(700, 300, 200, 20);
        supasswordfield.setBounds(700, 400, 200, 20);


        signuppanel.add(sign_up);
        signuppanel.add(signupbutton);
        signuppanel.add(suerrormessage);
        signuppanel.add(suusername);
        signuppanel.add(supassword);
        signuppanel.add(supasswordfield);
        signuppanel.add(suusernamefield);
        signuppage.add(signuppanel);
        signuppage.pack();
    }

    public static JFrame getHomescreen() {
        return homescreen;
    }

    public static JFrame getLoginframe() {
        return loginframe;
    }

    public static JFrame getSignupframe() {
        return signuppage;
    }

    public static JFrame getrecommendationframe() {
        return recframe;
    }

    public static JLabel getNewrecommendationlabel() {
        return newrecommendation;
    }

    public static JLabel getRewatchrecommendationlabel() {
        return rewatchrecommendation;
    }

    public static JButton getRewatchbutton() {
        return rewatchbutton;
    }

    public static JButton getDeleteaccbutton() {
        return deleteacc;
    }

    public static JButton getSignoutbutton() {
        return signout;
    }

    public static JButton getNewrecbutton() {
        return newrecbutton;
    }

    public static JButton getLoginbutton() {
        return loginbutton;
    }

    public static JButton getSignupbutton() {
        return signupbutton;
    }

    public static JPasswordField getLipasswordfield() {
        return lipasswordfield;
    }

    public static JPasswordField getSupasswordfield() {
        return supasswordfield;
    }

    public static JTextArea getLiusernamefield() {
        return liusernamefield;
    }

    public static JTextArea getSuusernamefield() {
        return suusernamefield;
    }

    public static JLabel getLierrormessage() {
        return lierrormessage;
    }

    public static JLabel getSuerrormessage() {
        return suerrormessage;
    }
}
