package step.learning.ioc.services.hash;

import step.learning.ioc.services.hash.HashService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Sha1HashService implements HashService {

    @Override
    public String hash(String input) {

        if (input == null) {
            input = "";
        }
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            StringBuilder sb = new StringBuilder();

            for (int b : digest.digest(input.getBytes(StandardCharsets.UTF_8))) {
                sb.append(String.format("%02x", b & 0xFF));
            }
            return sb.toString();

        } catch (Exception ex) {
            throw new IllegalArgumentException(":");
        }

    }

}
