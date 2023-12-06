package sanity.nil.authservice.application.interfaces.security;

public interface FingerprintUtils {

    String generateFingerprint(String userAgent, String userID);
}
