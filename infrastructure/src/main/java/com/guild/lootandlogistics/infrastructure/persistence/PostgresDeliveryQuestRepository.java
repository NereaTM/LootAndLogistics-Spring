package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.QuestId;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;

import java.util.Optional;

/**
 * ADAPTADOR DE SALIDA - guarda y recupera encargos en Postgres
 * Implementa el mismo puerto que InMemoryDeliveryQuestRepository y solo cambia dónde se guardan
 */
public class PostgresDeliveryQuestRepository implements DeliveryQuestRepository {

    private final DeliveryQuestJpaRepository jpaRepository;
    private final DeliveryQuestEntityMapper mapper;

    public PostgresDeliveryQuestRepository(
            DeliveryQuestJpaRepository jpaRepository,
            DeliveryQuestEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(DeliveryQuest quest) {
        jpaRepository.save(mapper.toEntity(quest));
    }

    @Override
    public Optional<DeliveryQuest> findById(QuestId id) {
        return jpaRepository.findById(id.value())
                .map(entity -> mapper.toDomain(entity));
    }
}