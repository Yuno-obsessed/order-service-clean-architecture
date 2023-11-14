package sanity.nil.order.presentation.api.middleware;


import io.micrometer.core.instrument.util.IOUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@Component
@WebFilter(urlPatterns = "/*")
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        try {
            chain.doFilter(wrappedRequest, res);
        } finally {
            logRequestBody(wrappedRequest);
        }
    }

    private static void logRequestBody(ContentCachingRequestWrapper request) throws IOException {

        log.info(" Request accepted: {} {}. Query String: {}\nPayload: {}",
                request.getRequestURI(), request.getMethod(), request.getQueryString(),
                IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8));
    }
}