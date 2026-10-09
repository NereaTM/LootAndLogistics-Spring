package com.guild.lootandlogistics.application.service;

import com.guild.lootandlogistics.domain.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Caso de uso: listar todos los encargos")
class FindAllDeliveryQuestsServiceTest {

    // repositorio falso y servicio nuevos en cada test
    private final FakeDeliveryQuestRepository repository = new FakeDeliveryQuestRepository();
    private final FindAllDeliveryQuestsService service = new FindAllDeliveryQuestsService(repository);

    // Las piezas para enviarlo al respositorio
    private final Location origin = new Location("El Gremio de Aventureros", "las Minas", "Stardew Valley");
    private final Location destination = new Location("La Posada del Poni Pisador", "Gran Camino Este", "Eriador");
    private final Cargo cargo = new Cargo("Baba de caracol", 5);
    private final Money reward = new Money(15, Currency.GOLD);
    private final DangerLevel dangerLevel = DangerLevel.LOW;

    @Test
    @DisplayName("Devuelve todos los encargos del tablón")
    void shouldReturnAllQuests() {
        repository.save(DeliveryQuest.create("Entrega de baba de caracol", origin, destination, cargo, reward, dangerLevel));
        repository.save(DeliveryQuest.create("Escolta de huevo de dragón", origin, destination, cargo, reward, dangerLevel));

        List<DeliveryQuest> result = service.findAll();

        assertThat(result)
                .extracting(quest -> quest.getTitle())
                .containsExactlyInAnyOrder("Entrega de baba de caracol", "Escolta de huevo de dragón");
    }

    @Test
    @DisplayName("Con el tablón vacío, devuelve una lista vacía")
    void shouldReturnEmptyListWhenNoQuests() {
        List<DeliveryQuest> result = service.findAll();

        assertThat(result).isEmpty();
    }
}