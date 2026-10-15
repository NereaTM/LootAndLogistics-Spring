package com.guild.lootandlogistics.domain.repository;

import com.guild.lootandlogistics.domain.entity.Currency;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.QuestId;
import com.guild.lootandlogistics.domain.entity.QuestStatus;

import java.util.List;
import java.util.Optional;

/**
 * PUERTO DE SALIDA - guarda y recupera encargos
 */
public interface DeliveryQuestRepository {

    /**
     * Guarda un encargo
     * @param quest encargo a guardar
     */
    void save(DeliveryQuest quest);

    /**
     * Busca un encargo por su id
     * @param id identificador del encargo
     * @return el encargo, o vacío si no existe
     */
    Optional<DeliveryQuest> findById(QuestId id);

    /**
     * Devuelve el listado de los encargos
     * @param filter criterios de búsqueda (null = todos los encargos)
     * @return lista de encargos
     */
    List<DeliveryQuest> findAll(DeliveryQuestFilter filter);

    /**
     * Las opciones de los filtros
     * @param status estado del encargo
     * @param cargoName nombre exacto de la carga
     * @param currency moneda de la recompensa
     */
    record DeliveryQuestFilter(QuestStatus status, String cargoName, Currency currency) {
    }
}
