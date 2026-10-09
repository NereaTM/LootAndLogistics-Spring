package com.guild.lootandlogistics.domain.usecase;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;

import java.util.List;

/**
 * PUERTO DE ENTRADA - consultar todos los encargos
 */
public interface FindAllDeliveryQuestsUseCase {

    /**
     * Devuelve a todos los encargos
     * @return lista encargos
     */
    List<DeliveryQuest> findAll();
}
