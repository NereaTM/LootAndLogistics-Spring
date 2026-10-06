package com.guild.lootandlogistics.application.service; // capa application: orquesta, no tiene reglas del juego

import com.guild.lootandlogistics.domain.entity.Cargo;
import com.guild.lootandlogistics.domain.entity.DangerLevel;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.Location;
import com.guild.lootandlogistics.domain.entity.Money;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
import com.guild.lootandlogistics.domain.usecase.CreateDeliveryQuestUseCase;

/**
 * Publica un nuevo encargo en el tablón: lo crea y lo guarda
 */
public class CreateDeliveryQuestService implements CreateDeliveryQuestUseCase {

    private final DeliveryQuestRepository repository;

    public CreateDeliveryQuestService(DeliveryQuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeliveryQuest create(String title,
                                Location origin, Location destination,
                                Cargo cargo, Money reward,
                                DangerLevel dangerLevel) {

        DeliveryQuest quest = DeliveryQuest.create(title, origin, destination, cargo, reward, dangerLevel);

        repository.save(quest);

        return quest;
    }
}