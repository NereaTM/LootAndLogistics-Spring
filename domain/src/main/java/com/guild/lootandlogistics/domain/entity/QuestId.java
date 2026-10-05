package com.guild.lootandlogistics.domain.entity;

import java.util.UUID;

/**
 * El identificador único de un encargo
 * Se genera automaticamente
 */
public record QuestId(UUID value) {

    public QuestId{
        if (value == null) {
            throw new IllegalArgumentException("El identificador del encargo es obligatorio");
        }
    }

    public static QuestId generate() {
        return new QuestId(UUID.randomUUID());
    }
}
