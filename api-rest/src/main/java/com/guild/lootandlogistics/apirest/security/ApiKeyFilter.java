package com.guild.lootandlogistics.apirest.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Filtro que comprueba la cabecera X-API-Key en cada petición
 * Si la clave es correcta, marca la petición como autenticada para Spring Security
 */
public class ApiKeyFilter extends OncePerRequestFilter {

    // Nombre de la cabecera, en un sitio para no repetirlo
    public static final String HEADER_NAME = "X-API-Key";

    // Clave válida; llega desde la configuración, no está escrita aquí
    private final String expectedApiKey;

    public ApiKeyFilter(String expectedApiKey) {
        this.expectedApiKey = expectedApiKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Leemos la clave que manda el cliente (null si no la manda)
        String receivedApiKey = request.getHeader(HEADER_NAME);

        if (receivedApiKey != null && isValid(receivedApiKey)) {
            var authentication = UsernamePasswordAuthenticationToken.authenticated(
                    "api-client",
                    null,
                    AuthorityUtils.NO_AUTHORITIES
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // El 401 lo decide Spring Security más adelante, no este filtro
        filterChain.doFilter(request, response);
    }

    // Comparación en tiempo constante para no dar pistas sobre la clave
    private boolean isValid(String receivedApiKey) {
        return MessageDigest.isEqual(
                receivedApiKey.getBytes(StandardCharsets.UTF_8),
                expectedApiKey.getBytes(StandardCharsets.UTF_8));
    }
}