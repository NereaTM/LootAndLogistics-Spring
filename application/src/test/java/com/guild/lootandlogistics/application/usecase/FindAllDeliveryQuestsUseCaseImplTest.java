package com.guild.lootandlogistics.application.usecase;

import com.guild.lootandlogistics.domain.entity.*;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Caso de uso: listar todos los encargos")
class FindAllDeliveryQuestsUseCaseImplTest {

    // repositorio falso y caso de uso nuevos en cada test
    private final FakeDeliveryQuestRepository repository = new FakeDeliveryQuestRepository();
    private final FindAllDeliveryQuestsUseCaseImpl useCase = new FindAllDeliveryQuestsUseCaseImpl(repository);

    // Las piezas para enviarlo al respositorio
    private final Location origin = new Location("El Gremio de Aventureros", "las Minas", "Stardew Valley");
    private final Location destination = new Location("La Posada del Poni Pisador", "Gran Camino Este", "Eriador");
    private final Cargo cargo = new Cargo("Baba de caracol", 5);
    private final Money reward = new Money(15, Currency.GOLD);
    private final DangerLevel dangerLevel = DangerLevel.LOW;
    private final DeliveryQuestRepository.DeliveryQuestFilter noFilter = new DeliveryQuestRepository.DeliveryQuestFilter(null, null, null);

    @Test
    @DisplayName("Sin filtro - Devuelve todos los encargos del tablón")
    void shouldReturnAllQuests() {
        repository.save(DeliveryQuest.create("Entrega de baba de caracol", origin, destination, cargo, reward, dangerLevel));
        repository.save(DeliveryQuest.create("Escolta de huevo de dragón", origin, destination, cargo, reward, dangerLevel));

        List<DeliveryQuest> result = useCase.findAll(noFilter);

        assertThat(result)
                .extracting(quest -> quest.getTitle())
                .containsExactlyInAnyOrder("Entrega de baba de caracol", "Escolta de huevo de dragón");
    }

    @Test
    @DisplayName("Con filtro - Devuelve solo los encargos que cumplen")
    void shouldReturnOnlyMatchingQuests() {
        // uno pagado en oro y otro en plata
        repository.save(DeliveryQuest.create("Entrega de baba de caracol", origin, destination, cargo, reward, dangerLevel));
        repository.save(DeliveryQuest.create("Escolta de huevo de dragón", origin, destination, cargo,
                new Money(30, Currency.SILVER), dangerLevel));

        // solo los de oro
        List<DeliveryQuest> result = useCase.findAll(new DeliveryQuestRepository.DeliveryQuestFilter(null, null, Currency.GOLD));

        assertThat(result)
                .extracting(quest -> quest.getTitle())
                .containsExactly("Entrega de baba de caracol");
    }

    @Test
    @DisplayName("Con el tablón vacío, devuelve una lista vacía")
    void shouldReturnEmptyListWhenNoQuests() {
        List<DeliveryQuest> result = useCase.findAll(noFilter);

        assertThat(result).isEmpty();
    }
}