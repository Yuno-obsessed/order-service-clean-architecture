package sanity.nil.order.presentation.api.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sanity.nil.order.application.common.consts.AccessError;
import sanity.nil.order.application.common.consts.AccessResponse;
import sanity.nil.order.application.common.dto.AccessCommandDTO;
import sanity.nil.order.application.common.dto.AccessDTO;
import sanity.nil.order.application.common.exceptions.AccessExpiredException;
import sanity.nil.order.application.common.exceptions.AuthBearerException;
import sanity.nil.order.application.order.interfaces.web.WebTemplate;
import sanity.nil.order.presentation.api.exception.response.ErrorResponse;

import java.io.IOException;
import java.util.UUID;

import static sanity.nil.order.presentation.api.exception.response.ErrorCode.ACCESS_DENIED;
import static sanity.nil.order.presentation.api.exception.response.ErrorCode.BAD_REQUEST;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private final WebTemplate<AccessDTO, AccessCommandDTO> webTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("AuthenticationFilter worked");
        HttpServletRequest request = (HttpServletRequest)  servletRequest;
        String bearer = request.getHeader("Authorization");
        if (bearer.isEmpty()) {
            throw new AuthBearerException();
        }
        AccessDTO accessDTO = webTemplate.get(new AccessCommandDTO(bearer));
        if (accessDTO.accessResponse.equals(AccessResponse.ACCESS_DENIED) &&
                accessDTO.accessError.equals(AccessError.INVALID_ACCESS_TOKEN)) {
            handleAuthInvalidResponse((HttpServletResponse) servletResponse);
            return;
        }
        if (accessDTO.accessResponse.equals(AccessResponse.ACCESS_EXPIRED) &&
                accessDTO.accessError.equals(AccessError.TOKEN_EXPIRED)) {
            handleAuthResponse((HttpServletResponse) servletResponse);
            throw new AccessExpiredException();
        }
        String rolesAsString = String.join(",", accessDTO.roles);
        servletRequest.setAttribute("roles", rolesAsString);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void handleAuthResponse(HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID(), ACCESS_DENIED.getReason());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private void handleAuthInvalidResponse(HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(UUID.randomUUID(), BAD_REQUEST.getReason());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
