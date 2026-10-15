package com.guild.lootandlogistics.application.usecase;

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
    public List<DeliveryQuest> findAll(DeliveryQuestFilter filter) {
        return quests.values().stream()
                // criterio a null = no filtra - si viene, tiene que coincidir
                .filter(quest -> filter.status() == null || quest.getStatus() == filter.status())
                .filter(quest -> filter.cargoName() == null || quest.getCargo().name().equals(filter.cargoName()))
                .filter(quest -> filter.currency() == null || quest.getReward().currency() == filter.currency())
                .toList();
    }
}
