package com.guild.lootandlogistics.boot;

import com.guild.lootandlogistics.apirest.security.ApiKeyFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(properties = "security.api-key=" + SecurityIT.GUILD_KEY)
@AutoConfigureMockMvc
@Testcontainers
@DisplayName("Seguridad - API key")
class SecurityIT {

    static final String GUILD_KEY = "Api-test-key";

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:18");

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Sin llave, la API responde 401")
    void shouldReturn401WithoutKey() throws Exception {
        mockMvc.perform(get("/quests/{id}", UUID.randomUUID()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Con una llave falsa, la API responde 401")
    void shouldReturn401WithInvalidKey() throws Exception {
        mockMvc.perform(get("/quests/{id}", UUID.randomUUID())
                        .header(ApiKeyFilter.HEADER_NAME, "llave-de-goblin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Con la llave del gremio, la petición llega al controller")
    void shouldPassWithValidKey() throws Exception {
        mockMvc.perform(get("/quests/{id}", UUID.randomUUID())
                        .header(ApiKeyFilter.HEADER_NAME, GUILD_KEY))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("La documentación de Swagger es accesible sin llave")
    void shouldAllowSwaggerWithoutKey() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk());
    }
}