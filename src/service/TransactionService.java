package service;

import java.util.List;

import dao.TransactionDAO;
import model.Transaction;

public class TransactionService {

    private TransactionDAO transactionDao;

    public TransactionService(TransactionDAO transactionDao) {
        this.transactionDao = transactionDao;
    }

    public void recordTransaction(int accountId, String type, double amount) {
        transactionDao.recordTransaction(accountId, type, amount);
    }

    public List<Transaction> getTransactionNyAccid(int accountId) {
        return transactionDao.getTransactionByAccID(accountId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    public List<Transaction> getTransactionByUserID(int UserId) {
        return transactionDao.getTransactionByUserID(UserId);
    }

    public Transaction getTransactionByID(int transId) {
        return transactionDao.getTransactionByID(transId);
    }

    public void updateTransaction(int id, String newType, double newAmount) {
        transactionDao.updateTransaction(id, newType, newAmount);
    }

    public void deleteTransaction(int id) {
        transactionDao.deleteTransaction(id);
    }

}
