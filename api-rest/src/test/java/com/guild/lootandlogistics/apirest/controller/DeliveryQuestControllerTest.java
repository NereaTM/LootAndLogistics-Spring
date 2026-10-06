package com.guild.lootandlogistics.apirest.controller;

import com.guild.lootandlogistics.apirest.mapper.DeliveryQuestMapperImpl;
import com.guild.lootandlogistics.domain.entity.*;
import com.guild.lootandlogistics.domain.usecase.CreateDeliveryQuestUseCase;
import com.guild.lootandlogistics.domain.usecase.GetDeliveryQuestUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeliveryQuestController.class)
@Import(DeliveryQuestMapperImpl.class)
@DisplayName("Controller: encargos")
class DeliveryQuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateDeliveryQuestUseCase createDeliveryQuestUseCase;

    @MockitoBean
    private GetDeliveryQuestUseCase getDeliveryQuestUseCase;

    private final DeliveryQuest quest = DeliveryQuest.create(
            "Entrega de baba de caracol",
            new Location("El Gremio de Aventureros", "las Minas", "Stardew Valley"),
            new Location("La Posada del Poni Pisador", "Gran Camino Este", "Eriador"),
            new Cargo("Baba de caracol", 5),
            new Money(15, Currency.GOLD),
            DangerLevel.LOW);

    // Mismo encargo en JSON, como lo mandaría el cliente
    private static final String VALID_JSON = """
            {
              "title": "Entrega de baba de caracol",
              "origin": { "name": "El Gremio de Aventureros", "street": "las Minas", "region": "Stardew Valley" },
              "destination": { "name": "La Posada del Poni Pisador", "street": "Gran Camino Este", "region": "Eriador" },
              "cargo": { "name": "Baba de caracol", "quantity": 5 },
              "reward": { "amount": 15, "currency": "GOLD" },
              "dangerLevel": "LOW"
            }
            """;

    // POST /quests - datos válidos
    @Test
    @DisplayName("Crea un encargo y responde 201 con todos sus datos")
    void shouldCreateQuestAndReturn201() throws Exception {
        when(createDeliveryQuestUseCase.create(any(), any(), any(), any(), any(), any())).thenReturn(quest);

        mockMvc.perform(post("/quests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(quest.getId().value().toString()))
                .andExpect(jsonPath("$.title").value("Entrega de baba de caracol"))
                .andExpect(jsonPath("$.origin.name").value("El Gremio de Aventureros"))
                .andExpect(jsonPath("$.origin.street").value("las Minas"))
                .andExpect(jsonPath("$.origin.region").value("Stardew Valley"))
                .andExpect(jsonPath("$.destination.name").value("La Posada del Poni Pisador"))
                .andExpect(jsonPath("$.destination.street").value("Gran Camino Este"))
                .andExpect(jsonPath("$.destination.region").value("Eriador"))
                .andExpect(jsonPath("$.cargo.name").value("Baba de caracol"))
                .andExpect(jsonPath("$.cargo.quantity").value(5))
                .andExpect(jsonPath("$.reward.amount").value(15))
                .andExpect(jsonPath("$.reward.currency").value("GOLD"))
                .andExpect(jsonPath("$.dangerLevel").value("LOW"))
                .andExpect(jsonPath("$.status").value("AVAILABLE"));
    }

    // POST /quests - el dominio rechaza los datos
    @Test
    @DisplayName("Responde 400 con el mensaje si el dominio rechaza el encargo")
    void shouldReturn400WhenDomainRejectsQuest() throws Exception {
        // Simulamos que el dominio lanza su excepción
        when(createDeliveryQuestUseCase.create(any(), any(), any(), any(), any(), any()))
                .thenThrow(new IllegalArgumentException("El origen y el destino no pueden ser el mismo"));

        mockMvc.perform(post("/quests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("El origen y el destino no pueden ser el mismo"));
    }
}