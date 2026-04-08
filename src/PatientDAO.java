import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // INSERT PATIENT
    public static void insertPatient(Patient p) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO patients(name, age, disease, medicine) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getDisease());
            ps.setString(4, p.getMedicine());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FETCH ALL PATIENTS
    public static List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM patients");

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("disease"),
                        rs.getString("medicine")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // DELETE PATIENT
    public static void deletePatient(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM patients WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}