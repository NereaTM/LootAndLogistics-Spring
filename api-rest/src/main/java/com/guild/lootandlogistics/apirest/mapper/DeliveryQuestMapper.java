package com.guild.lootandlogistics.apirest.mapper;

import com.guild.lootandlogistics.apirest.dto.CargoDTO;
import com.guild.lootandlogistics.apirest.dto.CreateDeliveryQuestRequestDTO;
import com.guild.lootandlogistics.apirest.dto.DeliveryQuestResponseDTO;
import com.guild.lootandlogistics.apirest.dto.LocationDTO;
import com.guild.lootandlogistics.apirest.dto.MoneyDTO;
import com.guild.lootandlogistics.domain.entity.Cargo;
import com.guild.lootandlogistics.domain.entity.DangerLevel;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.Location;
import com.guild.lootandlogistics.domain.entity.Money;
import com.guild.lootandlogistics.domain.entity.QuestId;
import org.mapstruct.Mapper;

import java.util.UUID;

/**
 * Convierte entre los DTOs de la API y el dominio
 * La implementación la genera MapStruct al compilar
 */
@Mapper(componentModel = "spring")
public interface DeliveryQuestMapper {

    // Entrada: DTO a dominio (piezas que pide CreateDeliveryQuestUseCase)

    Location toDomain(LocationDTO dto);

    Cargo toDomain(CargoDTO dto);

    Money toDomain(MoneyDTO dto);

    DangerLevel toDomain(CreateDeliveryQuestRequestDTO.DangerLevelEnum dangerLevel);

    // Salida: dominio a DTO

    DeliveryQuestResponseDTO toResponse(DeliveryQuest quest);

    // MapStruct no sabe sacar el UUID de QuestId por su cuenta
    default UUID map(QuestId id) {
        return id.value();
    }
}