package com.guild.lootandlogistics.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DeliveryQuest - creación")
public class DeliveryQuestTest {

    private final String title = "Entrega de baba de caracol";
    private final Location origin = new Location("El Gremio de Aventureros", "las Minas", "Stardew Valley");
    private final Location destination = new Location("La Posada del Poni Pisador", "Gran Camino Este", "Eriador");
    private final Cargo cargo = new Cargo("Baba de caracol", 5);
    private final Money reward = new Money(15, Currency.GOLD);
    private final DangerLevel dangerLevel = DangerLevel.LOW;

    @Test
    @DisplayName("Se crea con datos válidos, id generado y estado AVAILABLE")
    void shouldCreateQuestWithValidData() {
        DeliveryQuest quest = DeliveryQuest.create(title, origin, destination, cargo, reward, dangerLevel);

        assertThat(quest.getId()).isNotNull();                        // el dominio genera el id
        assertThat(quest.getTitle()).isEqualTo(title);
        assertThat(quest.getOrigin()).isEqualTo(origin);
        assertThat(quest.getDestination()).isEqualTo(destination);
        assertThat(quest.getCargo()).isEqualTo(cargo);
        assertThat(quest.getReward()).isEqualTo(reward);
        assertThat(quest.getDangerLevel()).isEqualTo(dangerLevel);
        assertThat(quest.getStatus()).isEqualTo(QuestStatus.AVAILABLE); // siempre nace en AVAILABLE
    }

    @Test
    @DisplayName("Rechaza un título nulo")
    void shouldRejectNullTitle() {
        assertThatThrownBy(() -> DeliveryQuest.create(null, origin, destination, cargo, reward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El título del encargo es obligatorio");
    }

    @Test
    @DisplayName("Rechaza un título en blanco")
    void shouldRejectBlankTitle() {
        assertThatThrownBy(() -> DeliveryQuest.create("   ", origin, destination, cargo, reward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El título del encargo es obligatorio");
    }

    @Test
    @DisplayName("Rechaza un origen nulo")
    void shouldRejectNullOrigin() {
        assertThatThrownBy(() -> DeliveryQuest.create(title, null, destination, cargo, reward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El origen del encargo es obligatorio");
    }

    @Test
    @DisplayName("Rechaza un destino nulo")
    void shouldRejectNullDestination() {
        assertThatThrownBy(() -> DeliveryQuest.create(title, origin, null, cargo, reward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El destino del encargo es obligatorio");
    }

    @Test
    @DisplayName("Rechaza un destino igual al origen")
    void shouldRejectSameOriginAndDestination() {
        Location sameAsOrigin = new Location("El Gremio de Aventureros", "las Minas", "Stardew Valley");

        assertThatThrownBy(() -> DeliveryQuest.create(title, origin, sameAsOrigin, cargo, reward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El origen y el destino del encargo no pueden ser el mismo");
    }

    @Test
    @DisplayName("Rechaza una carga nula")
    void shouldRejectNullCargo() {
        assertThatThrownBy(() -> DeliveryQuest.create(title, origin, destination, null, reward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La carga del encargo es obligatoria");
    }

    @Test
    @DisplayName("Rechaza una recompensa nula")
    void shouldRejectNullReward() {
        assertThatThrownBy(() -> DeliveryQuest.create(title, origin, destination, cargo, null, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La recompensa es obligatoria y mayor que 0");
    }

    @Test
    @DisplayName("Rechaza una recompensa de cero")
    void shouldRejectZeroReward() {
        Money zeroReward = new Money(0, Currency.GOLD);

        assertThatThrownBy(() -> DeliveryQuest.create(title, origin, destination, cargo, zeroReward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La recompensa es obligatoria y mayor que 0");
    }

    @Test
    @DisplayName("Rechaza una recompensa negativa")
    void shouldRejectNegativeReward() {
        Money negativeReward = new Money(-10, Currency.GOLD);

        assertThatThrownBy(() -> DeliveryQuest.create(title, origin, destination, cargo, negativeReward, dangerLevel))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La recompensa es obligatoria y mayor que 0");
    }

    @Test
    @DisplayName("Rechaza un nivel de peligro nulo")
    void shouldRejectNullDangerLevel() {
        assertThatThrownBy(() -> DeliveryQuest.create(title, origin, destination, cargo, reward, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El peligro del encargo es obligatorio");
    }
}