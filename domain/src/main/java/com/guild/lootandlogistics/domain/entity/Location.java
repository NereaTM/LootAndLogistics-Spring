package com.guild.lootandlogistics.domain.entity;

/**
 * Lugar del reino donde empieza o termina el encargo
 * El nombre es opcional porque está pensado para lugares conocidos
 * @param name
 * @param street
 * @param region
 */
public record Location(String name, String street, String region) {

    public Location {

        if (name != null && name.isBlank()) {
            throw new IllegalArgumentException("El nombre del lugar no puede estar en blanco");
        }

        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("La calle es obligatoria");
        }

        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("La region es obligatoria");
        }
    }
}
