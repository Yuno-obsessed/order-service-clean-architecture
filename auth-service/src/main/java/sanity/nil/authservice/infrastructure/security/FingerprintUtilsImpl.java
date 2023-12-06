package sanity.nil.authservice.infrastructure.security;

import org.springframework.context.annotation.Configuration;
import sanity.nil.authservice.application.interfaces.security.FingerprintUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class FingerprintUtilsImpl implements FingerprintUtils {

    public String generateFingerprint(String userAgent, String userID) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest((userAgent + userID).getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating fingerprint", e);
        }
    }
}
