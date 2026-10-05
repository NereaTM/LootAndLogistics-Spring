package com.guild.lootandlogistics.domain.entity;

/**
 * La mercancía que se transporta
 * @param name
 * @param quantity
 */
public record Cargo(String name, int quantity) {

    public Cargo {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre de la carga es necesario");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
    }
}
