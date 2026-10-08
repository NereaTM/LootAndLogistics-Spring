package com.guild.lootandlogistics.apirest.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Filtro: API key")
class ApiKeyFilterTest {

    // Clave que el filtro considera válida en estos tests
    private static final String GUILD_KEY = "llave-del-gremio";

    private final ApiKeyFilter filter = new ApiKeyFilter(GUILD_KEY);

    // Peticiones y respuestas falsas de Spring: no hace falta servidor
    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();
    private final MockFilterChain chain = new MockFilterChain();

    // El contexto de seguridad es global por hilo: lo limpiamos para que un test no contamine al siguiente
    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Con la llave del gremio, la petición queda autenticada")
    void shouldAuthenticateWithValidKey() throws Exception {
        // Mandamos la clave correcta en la cabecera
        request.addHeader(ApiKeyFilter.HEADER_NAME, GUILD_KEY);

        filter.doFilter(request, response, chain);

        // El filtro ha dejado un usuario autenticado en el contexto
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
        assertThat(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()).isTrue();
    }

    @Test
    @DisplayName("Con una llave falsa, la petición no se autentica")
    void shouldNotAuthenticateWithInvalidKey() throws Exception {
        request.addHeader(ApiKeyFilter.HEADER_NAME, "llave-de-goblin");

        filter.doFilter(request, response, chain);

        // Sin autenticación: más adelante Spring Security devolverá 401
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("Sin llave, la petición no se autentica")
    void shouldNotAuthenticateWithoutKey() throws Exception {
        // No añadimos cabecera
        filter.doFilter(request, response, chain);

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("El filtro siempre deja pasar la petición al siguiente eslabón")
    void shouldAlwaysContinueChain() throws Exception {
        // Aunque no haya clave, el filtro no corta: decide la configuración de seguridad
        filter.doFilter(request, response, chain);

        // MockFilterChain guarda la petición si alguien llamó a doFilter sobre ella
        assertThat(chain.getRequest()).isSameAs(request);
    }
}