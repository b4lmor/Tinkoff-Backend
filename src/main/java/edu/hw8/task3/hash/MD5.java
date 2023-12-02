package edu.hw8.task3.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static final String HASH_FUNCTION_NAME = "MD5";
    public static final int HEX = 16;
    public static final int MIN_LENGTH = 32;

    private MD5() {
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_FUNCTION_NAME);
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(HEX));

            while (hashText.length() < MIN_LENGTH) {
                hashText.insert(0, "0");
            }

            return hashText.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
