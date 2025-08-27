package com.library.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth-service", r -> r.path("/api/auth/**")
                .uri("lb://auth-service"))
            .route("book-service", r -> r.path("/api/books/**")
                .uri("lb://book-service"))
            .route("user-service", r -> r.path("/api/users/**")
                .uri("lb://user-service"))
            .route("transaction-service", r -> r.path("/api/transactions/**")
                .uri("lb://transaction-service"))
            .build();
    }
}
