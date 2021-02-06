package by.golik.finalproject.service;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Nikita Golik
 */
public final class Encryption {
    private Encryption() {

    }

    /**
     * Encrypts a password. The gensalt() method default param is
     * 10 but it lowered here, assuming better performance.
     */
    public static String encrypt (String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(5));
    }

    /**
     * Checks whether a plaintext password matches one that has been
     * hashed previously
     */
    public static Boolean isMatch(String plainPassword, String hashedPassword) {
        return (BCrypt.checkpw(plainPassword, hashedPassword));
    }
}
