package com.guild.lootandlogistics.domain.usecase;

import com.guild.lootandlogistics.domain.entity.Cargo;
import com.guild.lootandlogistics.domain.entity.DangerLevel;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.Location;
import com.guild.lootandlogistics.domain.entity.Money;

/**
 * PUERTO DE ENTRADA - publicar un nuevo encargo en el tablón
 */
public interface CreateDeliveryQuestUseCase {

    DeliveryQuest create(String title,
                         Location origin, Location destination,
                         Cargo cargo, Money reward,
                         DangerLevel dangerLevel);
}