import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
// import service.BankService;

import dao.AccountDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import service.AccountService;
import service.TransactionService;
import service.UserService;
import util.DBConnection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            UserService userService = new UserService(new UserDAO(conn));
            AccountService accountService = new AccountService(new AccountDAO(conn));
            TransactionService transactionService = new TransactionService(new TransactionDAO(conn));

            while (true) {
                System.out.println("=== Welcome to Bank Management System ===");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    boolean success = userService.registerUser(name, email, password);
                    if (success) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed.");
                    }

                } else if (choice == 2) {
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    int userId = userService.login(email, password);
                    if (userId != -1) {
                        System.out.println("Login successful!");
                        boolean loggedIn = true;
                        while (loggedIn) {
                            System.out.println("1. Create Account");
                            System.out.println("2. Deposit");
                            System.out.println("3. Withdraw");
                            System.out.println("4. View Balance");
                            System.out.println("5. Logout");
                            int op = scanner.nextInt();
                            scanner.nextLine();

                            switch (op) {
                                case 1:
                                    System.out.print("Enter account type (Savings/Current): ");
                                    String type = scanner.nextLine();
                                    accountService.createAccount(userId, type);
                                    break;
                                case 2:
                                    System.out.print("Enter account ID: ");
                                    int accId = scanner.nextInt();
                                    System.out.print("Enter amount: ");
                                    double depAmt = scanner.nextDouble();
                                    accountService.deposit(accId, depAmt);
                                    break;
                                case 3:
                                    System.out.print("Enter account ID: ");
                                    int wAccId = scanner.nextInt();
                                    System.out.print("Enter amount: ");
                                    double wAmt = scanner.nextDouble();
                                    accountService.WithDrawal(wAccId, wAmt);
                                    break;
                                case 4:
                                    System.out.print("Enter account ID: ");
                                    int bAccId = scanner.nextInt();
                                    double balance = accountService.getBalance(bAccId);
                                    System.out.println("Balance: " + balance);
                                    break;
                                case 5:
                                    loggedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }
                        }
                    } else {
                        System.out.println("Login failed.");
                    }
                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }

            scanner.close();
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("Error connecting Database " + e.getMessage());
        }

    }
}