package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repositorio en memoria de encargos")
class InMemoryDeliveryQuestRepositoryTest {

    // Repositorio real (no fake): aquí probamos el adaptador en sí
    private final InMemoryDeliveryQuestRepository repository = new InMemoryDeliveryQuestRepository();

    // Encargo de ejemplo para guardar en el repositorio
    private final DeliveryQuest quest = DeliveryQuest.create(
            "Entrega de baba de caracol",
            new Location("El Gremio de Aventureros", "las Minas", "Stardew Valley"),
            new Location("La Posada del Poni Pisador", "Gran Camino Este", "Eriador"),
            new Cargo("Baba de caracol", 5),
            new Money(15, Currency.GOLD),
            DangerLevel.LOW);

    @Test
    @DisplayName("Encuentra por id un encargo guardado")
    void shouldFindSavedQuestById() {
        repository.save(quest);

        assertThat(repository.findById(quest.getId())).contains(quest);
    }

    @Test
    @DisplayName("Devuelve vacío si el encargo no existe")
    void shouldReturnEmptyWhenQuestDoesNotExist() {
        // Como no guardo datos no me va a mostrar nada
        assertThat(repository.findById(quest.getId())).isEmpty();
    }
}