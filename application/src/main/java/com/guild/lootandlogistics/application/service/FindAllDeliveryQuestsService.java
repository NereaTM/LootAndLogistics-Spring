package com.guild.lootandlogistics.application.service;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
import com.guild.lootandlogistics.domain.usecase.FindAllDeliveryQuestsUseCase;

import java.util.List;

/**
 * Implementa el caso de uso para consultar el listado
 */
public class FindAllDeliveryQuestsService implements FindAllDeliveryQuestsUseCase {

    private final DeliveryQuestRepository repository;

    public FindAllDeliveryQuestsService(DeliveryQuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DeliveryQuest> findAll() {
        return repository.findAll();
    }
}
