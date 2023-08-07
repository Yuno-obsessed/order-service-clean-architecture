package sanity.nil.onlineshop.presentation.api.middleware;

import io.micrometer.core.instrument.util.IOUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@Component
@WebFilter(urlPatterns = "/*")
public class RequestCachingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CachedHttpRequest cachedHttpRequest = new CachedHttpRequest(request);
        log.info(" Request accepted: {} {}. Query String: {}\nPayload: {}",
                request.getRequestURI(), request.getMethod(), request.getQueryString(),
                IOUtils.toString(cachedHttpRequest.getInputStream(), StandardCharsets.UTF_8));
        filterChain.doFilter(cachedHttpRequest, response);
    }
}
