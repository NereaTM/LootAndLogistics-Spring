package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.Cargo;
import com.guild.lootandlogistics.domain.entity.Currency;
import com.guild.lootandlogistics.domain.entity.DangerLevel;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.Location;
import com.guild.lootandlogistics.domain.entity.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Mapper - traductor entre encargo y fila de la BD")
class DeliveryQuestEntityMapperTest {

    private final DeliveryQuestEntityMapper mapper = Mappers.getMapper(DeliveryQuestEntityMapper.class);

    @Test
    @DisplayName("Traduce el encargo entre el dominio y la BD")
    void roundTripKeepsAllData() {
        DeliveryQuest original = DeliveryQuest.create(
                "Llevar pociones",
                new Location(null, "Calle Mayor", "Valle Norte"),
                new Location("Torre del Mago", "Camino Alto", "Montes Grises"),
                new Cargo("Pociones", 3),
                new Money(50, Currency.GOLD),
                DangerLevel.LOW);

        DeliveryQuest back = mapper.toDomain(mapper.toEntity(original));

        assertThat(back).usingRecursiveComparison().isEqualTo(original);
    }
}