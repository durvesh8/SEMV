package movierec;

import java.sql.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    Connection con;
    ArrayList<String> usernames = new ArrayList<>();
    Statement stmt;
    ArrayList<String> movieswatched = new ArrayList<>();
    ArrayList<String> moviesnotwatched = new ArrayList<>();
    boolean contain, passeq;

    public ClientHandler(Socket clientSocket, Connection con) throws IOException, SQLException {
        this.client = clientSocket;
        this.con = con;
        stmt = con.createStatement();
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            // Getting the usernames
            ResultSet rs = stmt.executeQuery("SELECT username FROM users");
            while (rs.next()) {
                usernames.add(rs.getString(1));
            }
            while (true) {
                Statement stmt = con.createStatement();
                String request = in.readLine();
                // Checking for socket close case
                if (request.equals("911")) {
                    try {
                        in.close();
                        out.close();
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                // Log In request
                if (request.equals("1")) {
                    while(true) {
                        String username = in.readLine();
                        String password = in.readLine();
                        // Check if username exists
                        contain = usernames.contains(username);
                        if (contain) {
                            // Username existed. check for password
                            out.println("1");
                            rs = stmt.executeQuery("SELECT password FROM users WHERE username=\"" + username + "\";");
                            rs.next();
                            passeq = password.equals(rs.getString(1));
                            if (!passeq) {
                                // Password didn't match
                                out.println("0");
                            } else {
                                // Password matched
                                out.println("1");

                                while (true) {
                                    String clientresp;
                                    clientresp = in.readLine();
                                    // Sign out case
                                    if (clientresp.equals("3")) {
                                        break;
                                    } else if (clientresp.equals("x")) {
                                        stmt.executeUpdate("ALTER TABLE movietable drop column " + username + ";");
                                        break;
                                    }
                                    // Rewatch Case
                                    else if (clientresp.equals("2")) {
                                        rs = stmt.executeQuery("SELECT MovieName FROM movietable WHERE " + username + "=\"Y\";");
                                        while (rs.next()) {
                                            movieswatched.add(rs.getString(1));
                                        }
                                        String randomMovie;


                                        if (movieswatched.isEmpty()) out.println("404");
                                        else {
                                            out.println("1");
                                            int index = new Random().nextInt(movieswatched.size());
                                            randomMovie = movieswatched.get(index);
                                            out.println(randomMovie);
                                            movieswatched.remove(index);
                                        }
                                    }
                                    // New Recommendation Case
                                    else {
                                        rs = stmt.executeQuery("SELECT MovieName FROM movietable WHERE " + username + "=\"N\";");
                                        while (rs.next()) {
                                            moviesnotwatched.add(rs.getString(1));
                                        }
                                        String randomMovie = "";

                                        if (moviesnotwatched.isEmpty()) out.println("404");
                                        else {
out.println("1");
                                            int index = new Random().nextInt(moviesnotwatched.size());
                                            randomMovie = moviesnotwatched.get(index);
                                            out.println(randomMovie);

                                            moviesnotwatched.remove(index);


                                            stmt.executeUpdate("UPDATE movietable set " + username + "=\"Y\" where MovieName=\"" + randomMovie + "\"");
                                        }
                                    }
                                }
                                break;
                            }
                        }
                        // Username didn't match
                        else {
                            out.println("0");
                        }
                    }
                }
                // Sign Up Case
                if (request.equals("2")) {
                    while(true) {
                        String usernamesignup = in.readLine();
                        String passwordsignup = in.readLine();
                        if (usernames.contains(usernamesignup)) {
                            // Username Already Exists
                            out.println("0");
                        } else {
                            usernames.add(usernamesignup);
                            stmt.executeUpdate("ALTER TABLE movietable ADD " + usernamesignup + " varchar(1);");
                            stmt.executeUpdate("UPDATE movietable SET " + usernamesignup + "=\"N\"");
                            stmt.executeUpdate("INSERT INTO users values(\"" + usernamesignup + "\",\"" + passwordsignup + "\");");
                            out.println("1");
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
