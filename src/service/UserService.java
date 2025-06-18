package service;

import dao.UserDAO;
import util.PasswordUtil;

public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public boolean registerUser(String name, String email, String password) {

        if (!PasswordUtil.isValidEmail(email)) {
            System.out.println("Invalid email format. Email must end with '.com' and not contain numbers after '@'.");
            return false;
        }

        if (!PasswordUtil.isValidPassword(password)) {
            System.out.println(
                    "Password must be at least 8 characters long, contain 1 uppercase and 1 special character.");
            return false;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);
        return userDao.registerUser(name, email, hashedPassword);
    }

    public int login(String email, String inputPassword) {
        String storedHashedPassword = userDao.getPasswordByEmail(email);
        if (storedHashedPassword == null)
            return -1;

        if (PasswordUtil.checkPassword(inputPassword, storedHashedPassword)) {
            return userDao.getUserIdByEmail(email);
        }
        return -1;
    }

    // public int ValidateUSer(String email, String password) {
    // return userDao.ValidateUSer(email, password);
    // }

}
