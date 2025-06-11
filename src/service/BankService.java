// package service;

// import dao.UserDAO;
// import dao.AccountDAO;

// public class BankService {
//     private UserDAO userDAO = new UserDAO();
//     private AccountDAO accountDAO = new AccountDAO();

//     public boolean registerUser(String name, String email, String password) {
//         return userDAO.registerUser(name, email, password);
//     }

//     public int login(String email, String password) {
//         return userDAO.login(email, password);
//     }

//     public void createAccount(int userId, String type) {
//         accountDAO.createAccount(userId, type);
//     }

//     public void deposit(int accountId, double amount) {
//         double balance = accountDAO.getBalance(accountId);
//         if (balance == -1) {
//             System.out.println("Account not found.");
//             return;
//         }
//         accountDAO.updateBalance(accountId, balance + amount);
//         System.out.println("Deposit successful.");
//     }

//     public void withdraw(int accountId, double amount) {
//         double balance = accountDAO.getBalance(accountId);
//         if (balance == -1) {
//             System.out.println("Account not found.");
//             return;
//         }
//         if (amount > balance) {
//             System.out.println("Insufficient balance.");
//             return;
//         }
//         accountDAO.updateBalance(accountId, balance - amount);
//         System.out.println("Withdrawal successful.");
//     }

//     public double getBalance(int accountId) {
//         return accountDAO.getBalance(accountId);
//     }
// }