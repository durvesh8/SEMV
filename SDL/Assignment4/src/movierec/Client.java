package movierec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import movierec.frames.*;

public class Client {
    JFrame homescreen, signuppage, loginpage, recommendationpage;
    JButton signUp, login, exit, rewatchbutton, newrecbutton, signout, deleteacc, loginbutton, signupbutton;
    JLabel suerrormessage, lierrormessage, newrecommendation, rewatchrecommendation;
    JTextArea suusernamefield, liusernamefield;
    JPasswordField supasswordfield, lipasswordfield;
    public ActionListener homescreensu, homescreenli, actionListenersignup, actionListenerlogin, rewatchal, newrecal, signoutal, deleteaccal;
    BufferedReader keyboard, reader;
    PrintWriter out;

    public Client() {
        try {
            Socket socket = new Socket("127.0.0.1", 9169);
            new frames();
            homescreen = frames.getHomescreen();
            signUp = frames.getSignUp();
            login = frames.getLogin();
            exit = frames.getExit();
            homescreen.setVisible(true);
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            homescreensu = getHomescreensu();
            signUp.addActionListener(homescreensu);
            homescreenli = getHomescreenli();
            login.addActionListener(homescreenli);
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    homescreen.dispose();
                    System.exit(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Client();

    }

    public ActionListener signup() {
        actionListenersignup = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = suusernamefield.getText();
                String password = supasswordfield.getText();
                out.println(username);
                out.println(password);

                try {
                    String serverresp = reader.readLine();
                    if (serverresp.equals("0")) {
                        // Existing user condition
                        suerrormessage.setBounds(700, 200, 300, 30);
                        suerrormessage.setText("Username Already Exists!");
                    } else {
                        // Account will be created successfully here
                        signuppage.dispose();
                        homescreen.setVisible(true);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };
        return actionListenersignup;
    }


    public ActionListener login() {

        actionListenerlogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernamelogin = liusernamefield.getText();
                String passwordlogin = lipasswordfield.getText();
                out.println(usernamelogin);
                out.println(passwordlogin);

                try {
                    String serverresplogin = reader.readLine();
                    if (serverresplogin.equals("0")) {
                        lierrormessage.setText("Username Doesn't Exist!");
                    } else {
                        String serverresplog = reader.readLine();
                        if (serverresplog.equals("0")) {
                            lierrormessage.setText("Incorrect Password");
                        } else {
                            // Successful login
                            loginpage.setVisible(false);
                            recommendationpage = getrecommendationframe();
                            recommendationpage.setVisible(true);
                        }

                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        return actionListenerlogin;
    }

    public JFrame getrecommendationframe() {
        recommendationpage = frames.getrecommendationframe();
        rewatchbutton = frames.getRewatchbutton();
        newrecbutton = frames.getNewrecbutton();
        signout = frames.getSignoutbutton();
        deleteacc = frames.getDeleteaccbutton();
        newrecommendation = frames.getNewrecommendationlabel();
        rewatchrecommendation = frames.getRewatchrecommendationlabel();
        rewatchal = rewatch();
        rewatchbutton.addActionListener(rewatchal);
        newrecal = newrecommend();
        newrecbutton.addActionListener(newrecal);
        signoutal = signout();
        signout.addActionListener(signoutal);
        deleteaccal = deleteacc();
        deleteacc.addActionListener(deleteaccal);
        return recommendationpage;
    }

    public ActionListener newrecommend() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("1");
                try {
                    String statusrec = reader.readLine();
                    if (statusrec.equals("404")) {
                        newrecommendation.setText("We're out of movies :(");
                    } else {
                        String movierec = reader.readLine();
                        newrecommendation.setText(movierec);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
    }

    public ActionListener rewatch() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("2");
                try {
                    String statusrew = reader.readLine();
                    if (statusrew.equals("404")) {
                        rewatchrecommendation.setText("You haven't watched anything.");
                    } else {
                        String rewrec = reader.readLine();
                        rewatchrecommendation.setText(rewrec);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
    }

    public ActionListener signout() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("3");
                recommendationpage.dispose();
                System.exit(0);
            }
        };
    }

    public ActionListener deleteacc() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("x");
                recommendationpage.dispose();
                System.exit(0);
            }
        };
    }

    public ActionListener getHomescreensu() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println(2);
                homescreen.dispose();
                signuppage = frames.getSignupframe();
                suusernamefield = frames.getSuusernamefield();
                supasswordfield = frames.getSupasswordfield();
                suerrormessage = frames.getSuerrormessage();
                signupbutton = frames.getSignupbutton();
                ActionListener signupbuttonactionlistener = signup();
                signupbutton.addActionListener(signupbuttonactionlistener);
                signuppage.setVisible(true);
            }
        };
    }

    public ActionListener getHomescreenli() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println(1);
                loginpage = frames.getLoginframe();
                homescreen.dispose();
                liusernamefield = frames.getLiusernamefield();
                lipasswordfield = frames.getLipasswordfield();
                lierrormessage = frames.getLierrormessage();
                ActionListener loginbuttonactionlistener = login();
                loginbutton = frames.getLoginbutton();
                loginbutton.addActionListener(loginbuttonactionlistener);
                loginpage.setVisible(true);
            }
        };
    }
}