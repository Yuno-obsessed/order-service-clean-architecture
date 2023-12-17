package sanity.nil.library.middleware;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sanity.nil.library.services.data.Identity;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.library.web.consts.AccessResponse;
import sanity.nil.library.web.consts.ErrorResponse;
import sanity.nil.library.web.dto.AccessCommandDTO;
import sanity.nil.library.web.dto.AccessDTO;
import sanity.nil.library.web.dto.AccessError;
import sanity.nil.library.web.interfaces.WebTemplate;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private final WebTemplate<AccessDTO, AccessCommandDTO> webTemplate;
    private final ObjectMapper objectMapper;
    private final IdentityProvider identityProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("AuthenticationFilter worked");
        HttpServletRequest request = (HttpServletRequest)  servletRequest;
        String bearer = request.getHeader("Authorization");
        if (bearer == null || bearer.isEmpty()) {
            log.error("Authorization header is empty.");
            handleEmptyAuthorizationHeader((servletResponse));
            return;
        }
        AccessDTO accessDTO = webTemplate.get(new AccessCommandDTO(bearer));
        if (accessDTO.accessResponse.equals(AccessResponse.ACCESS_DENIED) &&
                accessDTO.accessError.equals(AccessError.INVALID_ACCESS_TOKEN)) {
            log.error("Token is invalid.");
            handleInvalidAccessToken(servletResponse);
            return;
        }
        if (accessDTO.accessResponse.equals(AccessResponse.ACCESS_EXPIRED) &&
                accessDTO.accessError.equals(AccessError.TOKEN_EXPIRED)) {
            log.error("Token is expired, refresh it to access.");
            handleExpiredToken(servletResponse);
            return;
        }
        identityProvider.holdIdentity(new Identity(accessDTO.userID, accessDTO.roles));
//        String rolesAsString = String.join(",", accessDTO.roles);
//        servletRequest.setAttribute("roles", rolesAsString);
        filterChain.doFilter(servletRequest, servletResponse);
    }



    private void handleEmptyAuthorizationHeader(ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID(), "Empty Authorization Header");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private void handleInvalidAccessToken(ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID(), "Invalid Access Token");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private void handleExpiredToken(ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID(), "Expired Access Token");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_GONE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}

