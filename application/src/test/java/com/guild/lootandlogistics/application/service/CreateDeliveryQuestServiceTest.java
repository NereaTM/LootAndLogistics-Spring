package com.guild.lootandlogistics.application.service;

import com.guild.lootandlogistics.domain.entity.*;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Caso de uso: crear encargo")
public class CreateDeliveryQuestServiceTest {

    // Repositorio falso, implementa el puerto de guardado en un Map para el Test
    static class FakeDeliveryQuestRepository implements DeliveryQuestRepository {
        final Map<QuestId, DeliveryQuest> quests = new HashMap<>();

        @Override
        public void save(DeliveryQuest quest) {
            quests.put(quest.getId(), quest); // guarda por id
        }

        @Override
        public Optional<DeliveryQuest> findById(QuestId id) {
            return Optional.ofNullable(quests.get(id)); // vacío si no existe
        }
    }

    // Datos compartidos
    private final String title = "Entrega de baba de caracol";
    private final Location origin = new Location("El Gremio de Aventureros", "las Minas", "Stardew Valley");
    private final Location destination = new Location("La Posada del Poni Pisador", "Gran Camino Este", "Eriador");
    private final Cargo cargo = new Cargo("Baba de caracol", 5);
    private final Money reward = new Money(15, Currency.GOLD);
    private final DangerLevel dangerLevel = DangerLevel.LOW;

    // Cada Test crea su propio repositorio y servicio entonces no se cruzan
    private final FakeDeliveryQuestRepository repository = new FakeDeliveryQuestRepository();
    private final CreateDeliveryQuestService service = new CreateDeliveryQuestService(repository);

    @Test
    @DisplayName("Crea y devuelve el encargo con sus datos")
    void shouldCreateAndReturnQuest (){
        DeliveryQuest quest = service.create(title, origin, destination, cargo, reward, dangerLevel);

        assertThat(quest.getId()).isNotNull();
        assertThat(quest.getTitle()).isEqualTo(title);
        assertThat(quest.getOrigin()).isEqualTo(origin);
        assertThat(quest.getDestination()).isEqualTo(destination);
        assertThat(quest.getCargo()).isEqualTo(cargo);
        assertThat(quest.getReward()).isEqualTo(reward);
        assertThat(quest.getDangerLevel()).isEqualTo(dangerLevel);
        assertThat(quest.getStatus()).isEqualTo(QuestStatus.AVAILABLE);
    }

    @Test
    @DisplayName("guarda el encargo en el repositorio")
    void shouldSaveQuestInRepository() {
        DeliveryQuest quest = service.create(title, origin, destination, cargo, reward, dangerLevel);

        assertThat(repository.findById(quest.getId())).contains(quest);
    }

    @Test
    @DisplayName("No guarda nada si los datos no son válidos")
    void shouldNotSaveInvalidQuest() {
        assertThatThrownBy(() -> service.create(null, origin, destination, cargo, reward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class);

        assertThat(repository.quests).isEmpty();
    }

}
