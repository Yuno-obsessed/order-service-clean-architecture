package sanity.nil.order.presentation.api.middleware;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sanity.nil.order.application.common.dto.AccessCommandDTO;
import sanity.nil.order.application.common.dto.AccessDTO;
import sanity.nil.order.application.common.exceptions.AuthBearerException;
import sanity.nil.order.application.order.interfaces.web.WebTemplate;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private final WebTemplate<AccessDTO, AccessCommandDTO> webTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("AuthenticationFilter worked");
        HttpServletRequest request = (HttpServletRequest)  servletRequest;
        String bearer = request.getHeader("Authorization");
        if (bearer.isEmpty()) {
            throw new AuthBearerException();
        }
//        if (!bearer.isEmpty()) {
//           bearer = bearer.substring(7);
//        } else {
//            throw new AuthBearerException();
//        }
        AccessDTO accessDTO = webTemplate.get(new AccessCommandDTO(bearer));
        String rolesAsString = String.join(",", accessDTO.roles);
        servletRequest.setAttribute("roles", rolesAsString);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
