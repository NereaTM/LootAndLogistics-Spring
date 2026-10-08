package com.guild.lootandlogistics.boot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Añade botón Authorize a swagger
 */
@Configuration
public class OpenApiConfiguration {

    private static final String SCHEME_NAME = "ApiKeyAuth";

    @Bean
    OpenApiCustomizer apiKeyCustomizer() {
        return openApi -> {
            if (openApi.getComponents() == null) {
                openApi.setComponents(new Components());
            }
            openApi.getComponents().addSecuritySchemes(SCHEME_NAME, new SecurityScheme()
                    .type(SecurityScheme.Type.APIKEY)
                    .in(SecurityScheme.In.HEADER)
                    .name("X-API-Key")
                    .description("Clave de acceso a la API del gremio"));
            openApi.addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));
        };
    }
}