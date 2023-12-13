package sanity.nil.library.config;

import org.springframework.stereotype.Component;

@Component
public class Services {

//    @Value("${application.auth.host}")
//    public String host;
//
//    @Value("${application.auth.port}")
//    public String port;

    public String getAuthServiceBaseURL() {
        return "http://auth-service:8003/api/v1";
    }
}
