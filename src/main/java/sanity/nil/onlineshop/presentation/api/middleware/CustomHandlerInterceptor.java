package sanity.nil.onlineshop.presentation.api.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import sanity.nil.onlineshop.presentation.api.exception.request.RequestIdGenerator;
import sanity.nil.onlineshop.presentation.api.exception.request.RequestIdHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class CustomHandlerInterceptor implements HandlerInterceptor {

    private final RequestIdHolder requestIdHolder;
    private final RequestIdGenerator requestIdGenerator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String id = request.getHeader("X-REQUEST-ID");
        if (id == null || id.isEmpty()) {
            UUID requestId = requestIdGenerator.generate();
            log.debug("New request: {}", requestId.toString());
            requestIdHolder.save(requestId);
        } else {
            requestIdHolder.save(UUID.fromString(id));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
