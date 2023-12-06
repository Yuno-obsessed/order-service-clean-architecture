package sanity.nil.authservice.application.consts;

public class JwtExceptions {

    private JwtExceptions() {

    }

    public static final String INVALID_JWT_SIGN = "Invalid JWT signature: {}";
    public static final String INVALID_JWT_TOKEN = "Invalid JWT token: {}";
    public static final String JWT_EXPIRED = "JWT token is expired: {}";
    public static final String JWT_UNSUPPORTED = "JWT token is unsupported: {}";
    public static final String JWT_EMPTY = "JWT claims string is empty: {}";
}
