package service;

import dao.AccountDAO;

public class AccountService {

    private AccountDAO accountDao;

    public AccountService(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    public void createAccount(int userId, String type) {
        accountDao.createAccount(userId, type);
    }

    public void deposit(int accountId, double newBalance) {
        accountDao.deposit(accountId, newBalance);
    }

    public void WithDrawal(int accountId, double amount) {
        accountDao.WithDrawal(accountId, amount);
    }

    public double getBalance(int accountId) {
        return accountDao.getBalance(accountId);
    }

}
