package multithread;

import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    Connection con = null;
    ArrayList<String> usernames = new ArrayList<String>();
    Statement stmt;

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
            ResultSet rs = stmt.executeQuery("SELECT username FROM users");
            while (rs.next()) {
                usernames.add(rs.getString(1));
            }
            while (true) {
                Statement stmt = con.createStatement();
                String request = in.readLine();
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
                if (request.equals("1")) {
                    String username = in.readLine();
                    String password = in.readLine();
                    boolean contain = usernames.contains(username);
                    if (contain) {
                        out.println("1");
                        rs = stmt.executeQuery("SELECT password FROM users WHERE username=\"" + username + "\";");
                        rs.next();
                        boolean passeq = password.equals(rs.getString(1));
                        if (!passeq) {
                            out.println("0");
                        } else {


                            out.println("1");

                            while (true) {
                                String clientresp = "";
                                clientresp = in.readLine();
                                if (clientresp.equals("3")) break;
                                else if (clientresp.equals("2")) {
                                    rs = stmt.executeQuery("SELECT MovieName FROM movietable WHERE " + username + "=\"Y\";");
                                    ArrayList<String> movieswatched = new ArrayList<String>();
                                    while (rs.next()) {
                                        movieswatched.add(rs.getString(1));
                                    }
                                    String randomMovie;
                                    String clientres;
                                    while (!movieswatched.isEmpty()) {
                                        int index = new Random().nextInt(movieswatched.size());
                                        randomMovie = movieswatched.get(index);
                                        out.println(randomMovie);
                                        clientres = in.readLine();
                                        if (clientres.equals("1")) break;
                                        movieswatched.remove(index);
                                    }
                                    if (movieswatched.isEmpty()) out.println("404");
                                } else {
                                    rs = stmt.executeQuery("SELECT MovieName FROM movietable WHERE " + username + "=\"N\";");
                                    ArrayList<String> moviesnotwatched = new ArrayList<String>();
                                    while (rs.next()) {
                                        moviesnotwatched.add(rs.getString(1));
                                    }
                                    String randomMovie = "";
                                    String clientres;
                                    int flag = 0;
                                    while (!moviesnotwatched.isEmpty()) {
                                        int index = new Random().nextInt(moviesnotwatched.size());
                                        randomMovie = moviesnotwatched.get(index);
                                        out.println(randomMovie);
                                        clientres = in.readLine();
                                        if (clientres.equals("1")) {
                                            flag = 1;
                                            break;
                                        }
                                        moviesnotwatched.remove(index);
                                    }
                                    if (moviesnotwatched.isEmpty()) out.println("404");
                                    if (flag == 1) {
                                        stmt.executeUpdate("UPDATE movietable set " + username + "=\"Y\" where MovieName=\"" + randomMovie + "\"");
                                    }
                                }
                            }

                        }
                    } else {
                        out.println("0");
                    }
                }
                if (request.equals("2")) {
                    String username = in.readLine();
                    String password = in.readLine();
                    if (usernames.contains(username)) {
                        out.println("0");
                    } else {
                        stmt.executeUpdate("ALTER TABLE movietable ADD " + username + " varchar(1);");
                        stmt.executeUpdate("UPDATE movietable SET " + username + "=\"N\"");
                        stmt.executeUpdate("INSERT INTO users values(\"" + username + "\",\"" + password + "\");");
                        out.println("1");

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
