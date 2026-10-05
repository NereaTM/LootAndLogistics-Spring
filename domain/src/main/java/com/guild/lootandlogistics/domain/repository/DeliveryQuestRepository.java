package com.guild.lootandlogistics.domain.repository;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.QuestId;

import java.util.Optional;

/**
 * PUERTO DE SALIDA - guarda y recupera encargos
 */
public interface DeliveryQuestRepository {

    void save(DeliveryQuest quest);
    Optional<DeliveryQuest> findById(QuestId id);
}
