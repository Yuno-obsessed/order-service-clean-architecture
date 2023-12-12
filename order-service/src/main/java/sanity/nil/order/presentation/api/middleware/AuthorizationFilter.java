package sanity.nil.order.presentation.api.middleware;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sanity.nil.order.application.common.exceptions.MethodException;
import sanity.nil.order.application.order.dto.query.PermissionQueryDTO;
import sanity.nil.order.application.order.interfaces.web.WebTemplate;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {

    private final WebTemplate<Boolean, PermissionQueryDTO> webTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("AuthorizationFilter worked");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        Matcher matcherPort = Pattern.compile(":(\\d+)").matcher(url);
        Matcher matcherURI = Pattern.compile(":(\\d+)/([^?:]*)(?:\\\\?|$)").matcher(url);
        String uri = null;
        String port = null;
        try {
            if (matcherPort.find()) {
                port = matcherPort.group(1);
                log.info("Port: " + port);
            } else {
                log.info("No match for port found");
            }
            if (matcherURI.find()) {
                uri = matcherURI.group(2);
                log.info("URI: " + uri);
            } else {
                log.info("No match for URI found");
            }
        } catch (Exception e) {
           throw new MethodException(url, e);
        }
        log.info("URL of request: {}", url);
        String roles = (String) servletRequest.getAttribute("roles");
        servletRequest.removeAttribute("roles");
        log.info("Roles: {}", roles);
        boolean access = webTemplate.get(new PermissionQueryDTO(roles, port, uri));
        log.info(String.valueOf(access));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
