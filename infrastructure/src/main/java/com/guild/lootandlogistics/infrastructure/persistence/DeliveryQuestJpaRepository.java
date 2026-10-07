package com.guild.lootandlogistics.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Acceso a la tabla delivery_quests con Spring Data
 * Spring genera los métodos (save, findById...) al arrancar, sin escribir SQL
 */
public interface DeliveryQuestJpaRepository extends JpaRepository<DeliveryQuestEntity, UUID> {

}