package com.nexsys.marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.nexsys.marketplace.util.Constants.*;

@Configuration
@EnableWebFluxSecurity
@Profile("security") // Solo se carga cuando el perfil "security" está activo
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/swagger-ui.html", "/v3/api-docs/**").permitAll()
                        // Permitir acceso a Platzi API
                        .pathMatchers(PLATZI_API_URL + PLATZI_API_PRODUCTS).permitAll()
                        .pathMatchers(PLATZI_API_URL + PLATZI_API_CATEGORIES).permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .authenticationSuccessHandler((webFilterExchange, authentication) -> {
                            webFilterExchange.getExchange().getResponse().getHeaders()
                                    .setLocation(URI.create("/swagger-ui.html")); // Redirige a Swagger
                            return Mono.empty();
                        })
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

}
