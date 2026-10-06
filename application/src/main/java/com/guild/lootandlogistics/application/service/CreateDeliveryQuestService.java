package com.guild.lootandlogistics.application.service; // capa application: orquesta, no tiene reglas del juego

import com.guild.lootandlogistics.domain.entity.Cargo;
import com.guild.lootandlogistics.domain.entity.DangerLevel;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.Location;
import com.guild.lootandlogistics.domain.entity.Money;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
import com.guild.lootandlogistics.domain.usecase.CreateDeliveryQuestUseCase;

/**
 * PUERTOS DE ENTRADA - publica un nuevo encargo
 */
public class CreateDeliveryQuestService implements CreateDeliveryQuestUseCase {

    private final DeliveryQuestRepository repository;

    public CreateDeliveryQuestService(DeliveryQuestRepository repository) {
        this.repository = repository;
    }

    /**
     * Publica un nuevo encargo - por defecto AVAILABLE con id generado
     * @param title título del encargo
     * @param origin lugar de recogida
     * @param destination lugar de entrega, distinto del origen
     * @param cargo mercancía a transportar
     * @param reward recompensa, mayor que 0
     * @param dangerLevel nivel de peligro
     * @return el encargo creado
     * @throws IllegalArgumentException si algún dato no es válido
     */
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