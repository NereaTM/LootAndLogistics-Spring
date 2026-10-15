package com.guild.lootandlogistics.domain.usecase;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;

import java.util.List;

/**
 * PUERTO DE ENTRADA - consultar todos los encargos
 */
public interface FindAllDeliveryQuestsUseCase {

    /**
     * Devuelve a todos los encargos
     * @param filter criterios de búsqueda
     * @return lista encargos
     */
    List<DeliveryQuest> findAll(DeliveryQuestRepository.DeliveryQuestFilter filter);
}
