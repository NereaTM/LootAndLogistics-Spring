package com.guild.lootandlogistics.domain.usecase;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.QuestId;
import com.guild.lootandlogistics.domain.exception.QuestNotFoundException;


/**
 * PUERTOS DE ENTRADA - comprobar un encargo por su id
 */
public interface GetDeliveryQuestUseCase {

    /**
     * Buscar un encargo por su ID
     * @param id del encargo
     * @return el encargo encontrado
     * @throws QuestNotFoundException si no existe ningún encargo con ese id
     */
    DeliveryQuest get(QuestId id);
}
