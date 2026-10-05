package com.guild.lootandlogistics.domain.entity;

/**
 * Ciclo de vida de un pedido
 * AVAILABLE -> ACCEPTED -> IN_TRANSIT -> DELIVERED (ciclo normal)
 */
public enum QuestStatus {
    AVAILABLE,
    ACCEPTED,
    IN_TRANSIT,
    DELIVERED,
    CANCELLED
}
