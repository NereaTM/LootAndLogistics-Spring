package com.guild.lootandlogistics.application.service;

import com.guild.lootandlogistics.domain.entity.*;
import com.guild.lootandlogistics.domain.exception.QuestNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("GetDeliveryQuestService")
class GetDeliveryQuestServiceTest {

    // repositorio falso y servicio nuevos en cada test
    private final FakeDeliveryQuestRepository repository = new FakeDeliveryQuestRepository();
    private final GetDeliveryQuestService service = new GetDeliveryQuestService(repository);

    // Las piezas para enviarlo al respositorio
    private final String title = "Entrega de baba de caracol";
    private final Location origin = new Location("El Gremio de Aventureros", "las Minas", "Stardew Valley");
    private final Location destination = new Location("La Posada del Poni Pisador", "Gran Camino Este", "Eriador");
    private final Cargo cargo = new Cargo("Baba de caracol", 5);
    private final Money reward = new Money(15, Currency.GOLD);
    private final DangerLevel dangerLevel = DangerLevel.LOW;

    // Se montan las piezas del encago
    private final DeliveryQuest quest = DeliveryQuest.create(title, origin, destination, cargo, reward, dangerLevel);

    @Test
    @DisplayName("Devuelve el encargo si existe")
    void shouldReturnQuestWhenExists() {
        repository.save(quest); // preparamos: el encargo ya está en el tablón

        DeliveryQuest found = service.get(quest.getId());

        assertThat(found).isEqualTo(quest);
    }

    @Test
    @DisplayName("Si no existe, lanza QuestNotFoundException")
    void shouldThrowWhenQuestNotFound() {
        QuestId unknownId = QuestId.generate(); // id que nunca se ha guardado

        assertThatThrownBy(() -> service.get(unknownId))
                .isInstanceOf(QuestNotFoundException.class)
                .hasMessage("No existe ningún encargo con el id " + unknownId.value());
    }
}