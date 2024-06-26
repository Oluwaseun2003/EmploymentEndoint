package com.employment.api.utilities;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String candidate, String hashedPassword){
        return BCrypt.checkpw(candidate, hashedPassword);
    }
}
