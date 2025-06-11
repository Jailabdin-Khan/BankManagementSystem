package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class TransactionDAO {
    private Connection conn;

    public TransactionDAO(Connection conn){
        this.conn= conn;
    }

    public void recordTransaction(int accountId, String type, double amount) {
        String query = "INSERT INTO transactions(account_id, type, amount) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            int rows= stmt.executeUpdate();
            if(rows>0){
                System.out.println("Transaction Recored Successfully");
            }else{
                System.out.println("Transaction Recoring failed");
            }
        } catch (SQLException e) {
            System.out.println("Transaction Recoring failed" + e.getMessage());
        }
    }

    public List<Transaction> getTransactionByAccID(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction txn=new Transaction();
                txn.setTransactionId(rs.getInt("transaction_Id"));
                txn.setAccountId(rs.getInt("account_Id"));
                txn.setType(rs.getString("type"));
                txn.setAmount(rs.getDouble("amount"));
                transactions.add(txn);
            }

        } catch (SQLException e) {
           System.out.println("Error getting Transaction by Account ID "+ e.getMessage());
        }
        return transactions;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";
        try (PreparedStatement stmt = conn.prepareStatement(query);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction txn=new Transaction();
                txn.setTransactionId(rs.getInt("transaction_Id"));
                txn.setAccountId(rs.getInt("account_Id"));
                txn.setType(rs.getString("type"));
                txn.setAmount(rs.getDouble("amount"));
                transactions.add(txn);
            }

        } catch (SQLException e) {
           System.out.println("Error getting All Transactions "+ e.getMessage());
        }
        return transactions;
    }

    public List<Transaction> getTransactionByUserID(int UserId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT t.* FROM transactions t JOIN accounts a On t.account_id  - a.id WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, UserId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction txn=new Transaction();
                txn.setTransactionId(rs.getInt("transaction_Id"));
                txn.setAccountId(rs.getInt("account_Id"));
                txn.setType(rs.getString("type"));
                txn.setAmount(rs.getDouble("amount"));
                transactions.add(txn);
            }

        } catch (SQLException e) {
           System.out.println("Error getting Transaction by USER ID "+ e.getMessage());
        }
        return transactions;
    }

    public Transaction getTransactionByID(int transId){
        Transaction trans=new Transaction();
        String query = "SELECt * FROM transactions WHERE transaction_id= ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, transId);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                trans.setTransactionId(rs.getInt("transaction_Id"));
                trans.setAccountId(rs.getInt("account_Id"));
                trans.setType(rs.getString("type"));
                trans.setAmount(rs.getDouble("amount"));
            }else{
                System.out.println("Transaction Not Found!!");
            }
        } catch (SQLException e) {
            // TODO: handle exception
             System.out.println("Error getting Transaction by ID "+ e.getMessage());

        }
        return trans;
    }

    public void updateTransaction(int id, String newType, double newAmount){
        String query="UPDATE transactions SET type=? , amount=? WHERE id=?";
        try(PreparedStatement stmt =conn.prepareStatement(query)) {
            stmt.setString(1, newType);
            stmt.setDouble(2, newAmount);
            stmt.setInt(3, id);
            int rows=stmt.executeUpdate();
            if(rows>0){
                System.out.println("Transaction Update Successful!");
            }else{
                System.out.println("Transaction Not Found");
            }
        } catch (Exception e) {
           System.out.println("Eroor Updating Transaction" + e.getMessage());
        }
    }

    public void deleteTransaction(int id){
        String query="DELETE FROM transactions WHERE id=?";
        try(PreparedStatement stmt =conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rows=stmt.executeUpdate();
            if(rows>0){
                System.out.println("Transaction Deleted Successful!");
            }else{
                System.out.println("Transaction Not Found");
            }
        } catch (Exception e) {
           System.out.println("Eroor Deleting Transaction" + e.getMessage());
        }
    }

}