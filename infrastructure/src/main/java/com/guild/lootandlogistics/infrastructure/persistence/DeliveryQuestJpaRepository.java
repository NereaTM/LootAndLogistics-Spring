package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.Currency;
import com.guild.lootandlogistics.domain.entity.QuestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Acceso a la tabla delivery_quests con Spring Data
 * Spring genera los métodos (save, findById...) al arrancar, sin escribir SQL
 */
public interface DeliveryQuestJpaRepository extends JpaRepository<DeliveryQuestEntity, UUID> {

    @Query("""
            SELECT q FROM DeliveryQuestEntity q
            WHERE (:status IS NULL OR q.status = :status)
              AND (:cargoName IS NULL OR q.cargoName = :cargoName)
              AND (:currency IS NULL OR q.rewardCurrency = :currency)
            """)
    List<DeliveryQuestEntity> findByFilter(
            @Param("status") QuestStatus status,
            @Param("cargoName") String cargoName,
            @Param("currency") Currency currency);
}