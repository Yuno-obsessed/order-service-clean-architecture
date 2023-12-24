package sanity.nil.library.config;

public class Services {

    public static String getAuthServiceBaseURL() {
        return "http://proxy:80/auth-service/auth";
    }

    public static String getRoleServiceBaseURL() {
        return "http://proxy:80/role-service/role";
    }
}
