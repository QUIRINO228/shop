package com.shop.gateway.configs;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> openEndpoint = List.of(
            "/users/registration",
            "/auth/authenticate",
            "/users/authenticate",
            "/amazons3/upload",
            "/amazons3/delete",
            "/products/create-product",
            "/products/graphql",
            "/products/delete/**"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoint.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}

