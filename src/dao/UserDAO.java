package dao;

import java.sql.*;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean registerUser(String name, String email, String password) {
        String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("User Already Exists!!");
        } catch (SQLException e) {
            System.out.println("Error registering user" + e.getMessage());
        }
        return false;
    }

    public String getPasswordByEmail(String email) {
        String sql = "SELECT password FROM users WHERE email=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching password: " + e.getMessage());
        }
        return null;
    }

    // ✅ New: get user_id by email
    public int getUserIdByEmail(String email) {
        String sql = "SELECT user_id FROM users WHERE email=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user ID: " + e.getMessage());
        }
        return -1;
    }

    // public int login(String email, String password) {
    // String sql = "SELECT user_id FROM users WHERE email=? AND password=?";
    // try (PreparedStatement stmt = conn.prepareStatement(sql)) {

    // stmt.setString(1, email);
    // stmt.setString(2, password);
    // ResultSet rs = stmt.executeQuery();
    // if (rs.next()) {
    // return rs.getInt("user_id");
    // }
    // } catch (SQLException e) {
    // System.out.println("Error logging In!!" + e.getMessage());
    // }
    // return -1;
    // }

    // public int ValidateUSer(String email, String password) {
    // String sql = "SELECT user_id,password FROM users WHERE email=? ";
    // try (PreparedStatement stmt = conn.prepareStatement(sql)) {
    // stmt.setString(1, email);
    // ResultSet rs = stmt.executeQuery();
    // if (rs.next()) {
    // String dbPass = rs.getString(password);
    // if (dbPass.equals(password)) {
    // return rs.getInt("id");
    // }
    // }
    // } catch (SQLException e) {
    // // TODO: handle exception
    // System.out.println("Error Validating user" + e.getMessage());
    // }
    // return -1;
    // }

}