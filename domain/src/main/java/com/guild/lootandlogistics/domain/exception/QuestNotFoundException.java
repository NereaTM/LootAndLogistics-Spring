package com.guild.lootandlogistics.domain.exception;

import com.guild.lootandlogistics.domain.entity.QuestId;

/**
 * Se lanza cuando no existe ningún encargo con el id buscado
 */
public class QuestNotFoundException extends RuntimeException {

    public QuestNotFoundException( QuestId id ) {
        super ("No existe ningún encargo con el id " + id.value());
    }
}
