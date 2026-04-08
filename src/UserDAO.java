import java.sql.*;

public class UserDAO {

    // REGISTER USER
    public static void register(User user) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());

            ps.executeUpdate();

            System.out.println("User Registered!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LOGIN CHECK
    public static boolean login(String username, String password, String role) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=? AND role=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // true if found

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}