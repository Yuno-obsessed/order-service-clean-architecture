package sanity.nil.userservice.presentation.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sanity.nil.userservice.application.dto.command.AccessCommand;
import sanity.nil.userservice.application.dto.command.LoginCommand;

@RestController
@RequestMapping("/api/v1/user")
@EnableWebSecurity
@RequiredArgsConstructor
public class TestController {

    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginCommand loginCommand) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginCommand.username, loginCommand.password);
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        return ResponseEntity.ok().body(authenticationResponse.isAuthenticated());
    }


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/access")
    public ResponseEntity<String> access(@RequestBody AccessCommand accessCommand) {

        Session session = new Session();
        String userAgent = request.getHeader("User-Agent");
        String ipAddress = request.getRemoteAddr();
        String user = request.getRemoteUser();
        return ResponseEntity.ok(String.format("ua: %s, ip: %s, u: %s", userAgent, ipAddress, user));
    }

}
