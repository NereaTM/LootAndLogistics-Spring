package com.guild.lootandlogistics.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("QuestId")
public class QuestIdTest {

    @Test
    @DisplayName("Se crea con un UUID válido")
    void shouldCreateQuestIdWithValidUuid() {
        UUID uuid = UUID.randomUUID();

        QuestId questId = new QuestId(uuid);

        assertThat(questId.value()).isEqualTo(uuid);
    }

    @Test
    @DisplayName("Genera un id con valor ")
    void shouldGenerateQuestIdWithValue() {
        QuestId questId = QuestId.generate();

        assertThat(questId.value()).isNotNull();
    }

    @Test
    @DisplayName("Genera id distintos cada vez")
    void shouldGenerateDifferentIds() {
        QuestId first = QuestId.generate();
        QuestId second = QuestId.generate();

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    @DisplayName("Rechaza un valor nulo")
    void shouldRejectNullValue() {
        assertThatThrownBy(() -> new QuestId(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El identificador del encargo es obligatorio");
    }



}
