package ro.db.vendor.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Password {

  private static int workload = 4;

  public static String hashPassword(String password_plaintext) {
    String salt = BCrypt.gensalt(workload);
    String hashed_password = BCrypt.hashpw(password_plaintext, salt);
    return (hashed_password);
  }
}
