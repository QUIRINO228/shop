package com.shop.gateway.configs;

import com.shop.authenticationservice.services.JwtUtil;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RefreshScope
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidator validator;

    public AuthenticationFilter(RouterValidator validator) {
        this.validator = validator;
    }

    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (validator.isSecured.test(request)) {
            if (authMissing(request)) {
                return onError(exchange);
            }
            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            if (jwtUtil.isExpired(token)) {
                return onError(exchange);
            }
        }
        if (isDeleteProductEndpoint(request)) {
            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private boolean isDeleteProductEndpoint(ServerHttpRequest request) {
        return request.getURI().getPath().startsWith("/products/delete/");
    }
}