import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;

public class Database {

    public void deleteUser(String deleteEmail) throws SQLException {
        String query = "DELETE FROM users WHERE email = ?";
        PreparedStatement ps = Objects.requireNonNull(connect()).prepareStatement(query);
        ps.setString(1, deleteEmail);
        int userDeleted = ps.executeUpdate();
        if(userDeleted>0) {
            System.out.println("Account deleted");
        } else {
            System.out.println("Error.");
        }
    }

    public void showUser(String showName) throws SQLException {
        String query = "SELECT name, email, adress FROM users WHERE name = ?";
        PreparedStatement ps = Objects.requireNonNull(connect()).prepareStatement(query);
        ps.setString(1, showName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Adress: " + rs.getString("adress"));
        } else {
            System.out.println("Info not found.");
        }
    }

    public void addUser(String email, String password, String name, String adress) throws SQLException {

        String hashedPassword = hashPassword(password);

        String query = "INSERT INTO users(email, password, name, adress) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = Objects.requireNonNull(connect()).prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, hashedPassword);
        ps.setString(3, name);
        ps.setString(4, adress);
        int addUser = ps.executeUpdate();
        if (addUser>0) {
            System.out.println("User added.");
        } else {
            System.out.println("Error.");
        }
    }
    public static Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:6306/itsakerhet", "root", "");
        } catch (Exception e) {
            System.out.println("FEL: " + e);
            return null;
        }
    }

    public String hashPassword(String password) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
