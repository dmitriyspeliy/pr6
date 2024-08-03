package ru.effective_mobile.tinyurl.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.effective_mobile.tinyurl.domain.entity.TinyUrl;
import ru.effective_mobile.tinyurl.service.TinyUrlService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Order(1)
@RequiredArgsConstructor
@Slf4j
public class UrlShortFilter implements Filter {

    @Value("${base.url}")
    private String baseUrl;

    private final TinyUrlService tinyUrlService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getRequestURL().toString().startsWith(baseUrl)) {
            Optional<TinyUrl> originalByShorter = tinyUrlService.getOriginalByShorter(req.getRequestURL().toString());
            if (originalByShorter.isPresent() && originalByShorter.get().getTtl().isAfter(LocalDateTime.now())) {
                log.info("Shorter link find in db, redirect to " + originalByShorter);
                res.sendRedirect(originalByShorter.get().getOriginal());
            }
        }
        chain.doFilter(request, response);
    }

}

