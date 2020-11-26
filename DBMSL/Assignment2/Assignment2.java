import java.sql.*;

public class Assignment2 {

    public static void main(String[] args) throws SQLException {
        String name, pass, url;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/te1db";
            name = "root";
            pass = "mysql";
            con = DriverManager.getConnection(url, name, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM book");
            // while ( rs.next() ){
            // System.out.printf("%s %-20s %d %2d %2d
            // %s\n",rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDate(6));
            // }
            stmt.executeUpdate("CREATE TABLE movie(movie_num int, movie_title varchar(20))");
            System.out.println("Table Created");
            stmt.executeUpdate("insert into movie values(1,'The Revenant')");
            stmt.executeUpdate("insert into movie values(2,'Lord of the Rings')");
            rs = stmt.executeQuery("select * from movie");
            while (rs.next()) {
                System.out.printf("%2d %s\n", rs.getInt(1), rs.getString(2));
            }
            stmt.executeUpdate("alter table movie rename to moviestable");
            System.out.println("Table Renamed from movie to moviestable");
            stmt.executeUpdate("truncate table moviestable");
            rs = stmt.executeQuery("select * from moviestable");
            while (rs.next()) {
                System.out.printf("%2d %s\n", rs.getInt(1), rs.getString(2));
            }
            System.out.println("Table Truncated");
            stmt.executeUpdate("insert into moviestable values(21,'21 Jump Street')");
            stmt.executeUpdate("insert into moviestable values(22,'22 Jump Street')");
            rs = stmt.executeQuery("select * from moviestable");
            while (rs.next()) {
                System.out.printf("%2d %s\n", rs.getInt(1), rs.getString(2));
            }
            stmt.executeUpdate("drop table moviestable");
            System.out.println("Table Dropped");

            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        con.close();

    }

}
