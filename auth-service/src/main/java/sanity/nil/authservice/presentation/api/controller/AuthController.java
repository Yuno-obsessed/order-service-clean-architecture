package sanity.nil.authservice.presentation.api.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanity.nil.authservice.application.dto.boundary.RefreshTokenDTO;
import sanity.nil.authservice.application.dto.command.AccessCommandDTO;
import sanity.nil.authservice.application.dto.command.CreateRefreshTokenDTO;
import sanity.nil.authservice.application.dto.command.LoginCommandDTO;
import sanity.nil.authservice.application.dto.interactor.LoginInteractorDTO;
import sanity.nil.authservice.application.dto.interactor.RefreshTokenInteractorDTO;
import sanity.nil.authservice.application.dto.response.AccessDTO;
import sanity.nil.authservice.application.dto.response.LoginDTO;
import sanity.nil.authservice.application.dto.response.NewRefreshTokenDTO;
import sanity.nil.authservice.application.exceptions.RefreshTokenIsEmptyException;
import sanity.nil.authservice.application.service.AuthService;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class AuthController {

    private final AuthService authService;

    // Save accessToken on client side
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginCommandDTO loginCommand,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        LoginInteractorDTO loginInteractorDTO = authService.loginCommand.handle(new CreateRefreshTokenDTO(loginCommand,
                request.getHeader("User-Agent"), request.getRemoteAddr()));
        Cookie cookie = new Cookie("REFRESH_TOKEN", loginInteractorDTO.refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/v1/auth");
        cookie.setMaxAge(loginInteractorDTO.refreshMaxAge);
        response.addCookie(cookie);
        return ResponseEntity
                .status(200)
                .body(new LoginDTO(loginInteractorDTO.accessToken));
    }

    // Save accessToken on client side
    @GetMapping("/refresh-token")
    public ResponseEntity<NewRefreshTokenDTO> refreshToken(HttpServletRequest request,
                                                           HttpServletResponse response) {
        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("REFRESH_TOKEN"))
                .findFirst()
                .orElseThrow(RefreshTokenIsEmptyException::new);
        String refreshToken = cookie.getValue();
        RefreshTokenInteractorDTO refreshTokenInteractorDTO = authService.refreshTokenCommand.handle(
                new RefreshTokenDTO(refreshToken, request.getHeader("User-Agent"),
                        request.getRemoteAddr()));
        cookie = new Cookie("REFRESH_TOKEN", refreshTokenInteractorDTO.refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/v1/auth");
        cookie.setMaxAge(refreshTokenInteractorDTO.maxAge);
        response.addCookie(cookie);
        return ResponseEntity
                .status(200)
                .body(new NewRefreshTokenDTO(refreshTokenInteractorDTO.accessToken));
    }

    @PostMapping("/access")
    public ResponseEntity<AccessDTO> access(@RequestBody AccessCommandDTO accessCommandDTO) {
        return ResponseEntity
                .status(200)
                .body(authService.accessCommand.handle(accessCommandDTO));
    }

    // Delete accessToken on client side
    @PutMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request,
                                       HttpServletResponse response) {
        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("REFRESH_TOKEN"))
                .findFirst()
                .orElseThrow(RefreshTokenIsEmptyException::new);
        authService.logoutCommand.handle(cookie.getValue());
        Cookie deletedCookie = new Cookie("REFRESH_TOKEN", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/api/v1/auth");
        deletedCookie.setMaxAge(0);
        response.addCookie(deletedCookie);
        return ResponseEntity
                .status(204)
                .build();
    }
}
