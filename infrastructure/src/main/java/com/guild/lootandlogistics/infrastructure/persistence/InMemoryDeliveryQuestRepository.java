package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.QuestId;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Adaptador de persistencia en memoria para los encargos del gremio
 * Implementa el puerto del dominio guardando los encargos en un Map
 */
public class InMemoryDeliveryQuestRepository implements DeliveryQuestRepository {

    // Map id -> encargo
    // ConcurrentHashMap es porque spring facilita la llegada de peticiones
    private final Map<QuestId, DeliveryQuest> quests = new ConcurrentHashMap<>();

    @Override
    public void save(DeliveryQuest quest) {
        // put para el id
        quests.put(quest.getId(), quest);
        // Devolvemos el mismo encargo, como pide el puerto
    }

    @Override
    public Optional<DeliveryQuest> findById(QuestId id) {
        // get para el id
        return Optional.ofNullable(quests.get(id));
    }
}