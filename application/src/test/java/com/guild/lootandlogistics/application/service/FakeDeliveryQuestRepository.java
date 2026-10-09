package com.guild.lootandlogistics.application.service;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.QuestId;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;

import java.util.*;

/**
 * Repositorio falso para tests: guarda los encargos en un Map
 */
public class FakeDeliveryQuestRepository implements DeliveryQuestRepository {

    final Map<QuestId, DeliveryQuest> quests = new HashMap<>();

    @Override
    public void save(DeliveryQuest quest) {
        quests.put(quest.getId(), quest); // guarda por id
    }

    @Override
    public Optional<DeliveryQuest> findById(QuestId id) {
        return Optional.ofNullable(quests.get(id)); // vacío si no existe
    }

    @Override
    public List<DeliveryQuest> findAll() {
        return new ArrayList<>(quests.values());
    }
}
