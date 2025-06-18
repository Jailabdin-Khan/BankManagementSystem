package util;

import java.util.regex.*;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static boolean isValidEmail(String email) {
        String emailpattern = "^[A-Za-z0-9+_.-]+@[A-Za-z.]+\\.com$";
        return Pattern.matches(emailpattern, email);
    }

    public static boolean isValidPassword(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}:\";'<>?,./]).{8,}$";
        return Pattern.matches(pattern, password);
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // 12 is work factor
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
