import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "sahil310326@11:40"   // change if your password is different
            );
            System.out.println("Connected to MySQL!");
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}