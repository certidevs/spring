package com.example.ratelimit;

import com.example.service.SubscriptionService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private SubscriptionService service;
    private Map<String, Bucket> cache = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username = Optional.ofNullable(
                        SecurityContextHolder.getContext().getAuthentication()
                ).map(Authentication::getPrincipal)
                .map(String.class::cast)
                .orElseThrow();

        Bucket bucket = cache.computeIfAbsent(username, this::createBucket);

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            response.addHeader("X-Api-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            long remaining = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader("X-Api-Rate-Limit-Remaining-Wait-Seconds", String.valueOf(remaining));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "Api rate limit consumed. You have to wait for refill.");
            return false;
        }
    }

    private Bucket createBucket(String username) {
        var subscription = service.findByUserName(username);
        Long capacity = subscription.getCapacity();
        Long minutes = subscription.getRefillMinutes();
        var refill = Refill.intervally(capacity, Duration.ofMinutes(minutes));
        var bandwidth = Bandwidth.classic(capacity, refill);
        return Bucket.builder().addLimit(bandwidth).build();
    }
}
