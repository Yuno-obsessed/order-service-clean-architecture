package sanity.nil.library.middleware;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sanity.nil.library.exceptions.MethodException;
import sanity.nil.library.services.interfaces.IdentityProvider;
import sanity.nil.library.web.consts.ErrorResponse;
import sanity.nil.library.web.consts.PermissionError;
import sanity.nil.library.web.dto.PermissionQueryDTO;
import sanity.nil.library.web.dto.RolePermissionDTO;
import sanity.nil.library.web.interfaces.WebTemplate;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {

    private final WebTemplate<RolePermissionDTO, PermissionQueryDTO> webTemplate;
    private final ObjectMapper objectMapper;
    private final IdentityProvider identityProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("AuthorizationFilter worked");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        log.info("URL of request: {}", url);
        Matcher matcherURI = Pattern.compile("http://localhost(/api/v1/[^/]+(?:/[^/]+)*)").matcher(url);
        String uri = null;
        try {
            if (matcherURI.find()) {
                uri = matcherURI.group(1);
                log.info("URI: " + uri);
            } else {
                log.info("No match for URI found");
            }
        } catch (Exception e) {
           throw new MethodException(url, e);
        }
        String roles = String.join(",", identityProvider.getCurrentIdentity().roles);
        log.info("Roles: {}", roles);
        RolePermissionDTO rolePermissionDTO = webTemplate.get(new PermissionQueryDTO(roles, uri, request.getMethod()));
        log.info("Role access to resource was " + (rolePermissionDTO.hasAccess ? "approved" : "denied"));
        if (rolePermissionDTO.permissionError != null &&
                rolePermissionDTO.permissionError.equals(PermissionError.NO_ROLE)) {
           handleNoRoleAccess(servletResponse);
           return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void handleNoRoleAccess(ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID(), "No access for such role to this resource.");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
