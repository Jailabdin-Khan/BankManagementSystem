package service;

import dao.UserDAO;

public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public boolean registerUser(String name, String email, String password) {
        return userDao.registerUser(name, email, password);
    }

    public int login(String email, String password) {
        return userDao.login(email, password);
    }

    public int ValidateUSer(String email, String password) {
        return userDao.ValidateUSer(email, password);
    }

}
