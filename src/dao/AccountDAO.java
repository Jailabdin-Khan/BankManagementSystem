package dao;

import java.sql.*;

public class AccountDAO {
    private Connection conn;

    public AccountDAO(Connection conn) {
        this.conn = conn;
    }

    public void createAccount(int userId, String type) {
        String sql = "INSERT INTO accounts(user_id, account_type, balance) VALUES (?, ?, 0)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.setString(2, type);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Creating account failed!");
            } else {
                ResultSet generatedkeys = stmt.getGeneratedKeys();
                if (generatedkeys.next()) {
                    int accountId = generatedkeys.getInt(1);
                    System.out.println("Account created with ID" + accountId);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error creating account" + e.getMessage());
        }
    }

    public void deposit(int accountId, double newBalance) {
        String query = "UPDATE account SET balance=balance + ? where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Account not Found");
            } else {
                System.out.format(" %d /- Deposited successfully!!", newBalance);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error during deposit " + e.getMessage());
        }
    }

    public void WithDrawal(int accountId, double amount) {
        String query = "SELECT balance FROM account WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance > amount) {
                    String UpdateQuery = "UPDATE account SET balance=balance - ? where id = ?";
                    try (PreparedStatement upstmt = conn.prepareStatement(UpdateQuery)) {
                        upstmt.setDouble(1, amount);
                        upstmt.setInt(2, accountId);
                        upstmt.executeUpdate();
                    }
                } else {
                    System.out.println("Insufficient Balance");
                }
            } else {
                System.out.println("Account Not Found");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error during withdrawal " + e.getMessage());
        }
    }

    public double getBalance(int accountId) {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            System.out.println("Error Fetching Balance" + e.getMessage());
        }
        return 0.0;
    }

}