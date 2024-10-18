package com.nexsys.marketplace.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Order(-2)
@Component
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable error) {
        var response = exchange.getResponse();
        var bufferFactory = response.bufferFactory();

        // Manejo de excepciones específicas
        if (error instanceof CustomNotFoundException) {
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return response.writeWith(Mono.just(bufferFactory.wrap("Not Found".getBytes(StandardCharsets.UTF_8))));
        }

        // Manejo de otros errores
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.writeWith(Mono.just(bufferFactory.wrap(("Internal Server Error: " + error.getMessage().getBytes(StandardCharsets.UTF_8)).getBytes())));
    }
}
