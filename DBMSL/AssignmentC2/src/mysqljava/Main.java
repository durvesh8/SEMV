package mysqljava;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Statement stmt;
        Scanner scanner = new Scanner(System.in);
        ResultSet rs;
        String username, pass, url;
        Connection con;
        url = "jdbc:mysql://localhost:3306/c2";
        Class.forName("com.mysql.cj.jdbc.Driver");
        username = "root";
        pass = "mysql";
        con = DriverManager.getConnection(url, username, pass);
        stmt = con.createStatement();
        System.out.println("-------------Welcome to Employee Database!-----------");
        int basechoice = 0,subchoice;
        while(basechoice!=5){
            System.out.println("1. View Database   2. Insert Record   3. Update Records  4. Delete Records  5. Exit");
            basechoice = scanner.nextInt();
            switch (basechoice){
                case 1:
                    System.out.println("1. Show all  2. Show Selective");
                    subchoice = scanner.nextInt();
                    switch (subchoice){
                        case 1:
                            rs = stmt.executeQuery("SELECT * from employee");
                            while(rs.next()){
                                System.out.println(rs.getString(1)+" \t\t -  "+rs.getInt(2)+
                                        " \t\t -  "+rs.getInt(3)+" \t\t -  "+rs.getString(4));
                            }
                            break;
                        case 2:
                            scanner.nextLine();
                            int whereflag=0;
                            System.out.println("Enter your where column");
                            String wherecolumn = scanner.nextLine();
                            if(wherecolumn.equals("name")||wherecolumn.equals("city")){
                                whereflag=1;
                            }
                            System.out.println("Enter your where condition e.g >,=,<");
                            String wherecondition = scanner.nextLine();
                            System.out.println("Enter your where value");
                            String where = scanner.nextLine();
                            if(whereflag==1){
                                where = "\""+where+"\"";
                            }
                            rs = stmt.executeQuery("SELECT * from employee WHERE "+wherecolumn+wherecondition+where+";");
                            while(rs.next()){
                                System.out.println(rs.getString(1)+" \t\t -  "+rs.getInt(2)+
                                        " \t\t -  "+rs.getInt(3)+" \t\t -  "+rs.getString(4));
                            }
                            break;
                    }
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String employeename = scanner.nextLine();
                    System.out.print("Enter EmployeeID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Salary: ");
                    int salary = scanner.nextInt();
                    System.out.print("Enter City: ");
                    scanner.nextLine();
                    String city = scanner.nextLine();
                    stmt.executeUpdate("INSERT INTO employee values (\""+employeename+"\","+id+","+salary+",\""+city+"\");");
                    System.out.println("-------------Entry inserted--------------");
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Enter Column to be updated");
                    String updatecolumn = scanner.nextLine();
                    int flag =0;
                    if(updatecolumn.equals("name")||updatecolumn.equals("city")){
                        flag=1;
                    }
                    System.out.println("1. Update all  2. Update Selective");
                    subchoice = scanner.nextInt();
                    switch (subchoice){
                        case 1:
                            scanner.nextLine();
                            System.out.println("Enter new value of the column");
                            String value = scanner.nextLine();
                            if(flag==1){
                                value = "\""+value+"\"";
                            }
                            stmt.executeUpdate("UPDATE employee SET "+updatecolumn+"="+value+";");
                            System.out.println("-------------Values Updated-------------");
                            break;
                        case 2:
                            scanner.nextLine();
                            int whereflag=0;
                            System.out.println("Enter your where column");
                            String wherecolumn = scanner.nextLine();
                            if(wherecolumn.equals("name")||wherecolumn.equals("city")){
                                whereflag=1;
                            }
                            System.out.println("Enter your where condition e.g >,=,<");
                            String wherecondition = scanner.nextLine();
                            System.out.println("Enter your where value");
                            String where = scanner.nextLine();
                            if(whereflag==1){
                                where = "\""+where+"\"";
                            }
                            System.out.println("Enter new value of the column");
                            String newvalue = scanner.nextLine();
                            if(flag==1){
                                newvalue = "\""+newvalue+"\"";
                            }
                            stmt.executeUpdate("UPDATE employee SET "+
                                    updatecolumn+"="+newvalue+" WHERE "+wherecolumn+wherecondition+where+";");
                            System.out.println("-------------Values Updated-------------");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("1. Delete all  2. Delete Selective");
                    subchoice = scanner.nextInt();
                    switch (subchoice){
                        case 1:
                            stmt.executeUpdate("DELETE FROM employee;");
                            System.out.println("-------------Values Deleted-------------");
                            break;
                        case 2:
                            scanner.nextLine();
                            int whereflag=0;
                            System.out.println("Enter your where column");
                            String wherecolumn = scanner.nextLine();
                            if(wherecolumn.equals("name")||wherecolumn.equals("city")){
                                whereflag=1;
                            }
                            System.out.println("Enter your where condition e.g >,=,<");
                            String wherecondition = scanner.nextLine();
                            System.out.println("Enter your where value");
                            String where = scanner.nextLine();
                            if(whereflag==1){
                                where = "\""+where+"\"";
                            }
                            stmt.executeUpdate("DELETE FROM employee WHERE "+wherecolumn+wherecondition+where+";");
                            System.out.println("-------------Values Deleted-------------");
                            break;
                    }
                    break;
                case 5:
                    break;
            }
        }
    }
}
