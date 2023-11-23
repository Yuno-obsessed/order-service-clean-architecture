package sanity.nil.userservice.presentation.api.middleware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityMiddleware {

    private static final String[] WHITE_LIST = {
            "/api/v1/user/access",
            "/api/v1/user/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        SecurityContextRepository repo = new HttpSessionSecurityContextRepository();
        http
                .csrf(c -> c.disable())
                .securityContext((context) -> context.securityContextRepository(repo))
                .authorizeHttpRequests(c -> c
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(h -> h
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .maximumSessions(1)
                )
                .formLogin(c -> c.disable())
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }
}
