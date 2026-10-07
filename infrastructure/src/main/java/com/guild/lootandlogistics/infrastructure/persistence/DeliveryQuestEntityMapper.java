package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.Cargo;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.Location;
import com.guild.lootandlogistics.domain.entity.Money;
import com.guild.lootandlogistics.domain.entity.QuestId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Traductor entre el encargo del dominio (DeliveryQuest) y la fila de la BD (DeliveryQuestEntity).
 * toEntity lo genera MapStruct; toDomain va a mano porque usa restore().
 */
@Mapper
public interface DeliveryQuestEntityMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "originName", source = "origin.name")
    @Mapping(target = "originStreet", source = "origin.street")
    @Mapping(target = "originRegion", source = "origin.region")
    @Mapping(target = "destinationName", source = "destination.name")
    @Mapping(target = "destinationStreet", source = "destination.street")
    @Mapping(target = "destinationRegion", source = "destination.region")
    @Mapping(target = "cargoName", source = "cargo.name")
    @Mapping(target = "cargoQuantity", source = "cargo.quantity")
    @Mapping(target = "rewardAmount", source = "reward.amount")
    @Mapping(target = "rewardCurrency", source = "reward.currency")
    DeliveryQuestEntity toEntity(DeliveryQuest quest);

    default DeliveryQuest toDomain(DeliveryQuestEntity entity) {
        return DeliveryQuest.restore(
                new QuestId(entity.getId()),
                entity.getTitle(),
                new Location(entity.getOriginName(), entity.getOriginStreet(), entity.getOriginRegion()),
                new Location(entity.getDestinationName(), entity.getDestinationStreet(), entity.getDestinationRegion()),
                new Cargo(entity.getCargoName(), entity.getCargoQuantity()),
                new Money(entity.getRewardAmount(), entity.getRewardCurrency()),
                entity.getDangerLevel(),
                entity.getStatus());
    }

}
