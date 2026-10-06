package com.guild.lootandlogistics.application.service;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.QuestId;
import com.guild.lootandlogistics.domain.exception.QuestNotFoundException;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
import com.guild.lootandlogistics.domain.usecase.GetDeliveryQuestUseCase;

/**
 * Consulta un encargo del tablón por su id
 */
public class GetDeliveryQuestService implements GetDeliveryQuestUseCase {

    private final DeliveryQuestRepository repository;

    public GetDeliveryQuestService(DeliveryQuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeliveryQuest get(QuestId id) {
        return repository.findById(id)
                .orElseThrow(() -> new QuestNotFoundException(id));
    }
}