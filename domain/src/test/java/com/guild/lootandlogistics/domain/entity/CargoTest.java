package com.guild.lootandlogistics.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Cargo")
public class CargoTest {

    @Test
    @DisplayName("Se crea con nombre y cantidad válidos")
    void shouldCreateCargoWithValidData() {
        Cargo cargo = new Cargo ("Poción de vida", 12);

        assertThat(cargo.name()).isEqualTo("Poción de vida");
        assertThat(cargo.quantity()).isEqualTo(12);
    }

    @Test
    @DisplayName("Rechaza nombre nulo")
    void shouldRejectNullName() {
        assertThatThrownBy(() -> new Cargo(null, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El nombre de la carga es necesario");
    }

    @Test
    @DisplayName("Rechaza nombre vacío")
    void shouldRejectEmptyName() {
        assertThatThrownBy(() -> new Cargo("", 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El nombre de la carga es necesario");
    }

    @Test
    @DisplayName("Rechaza nombre en blanco")
    void shouldRejectBlankName() {
        assertThatThrownBy(() -> new Cargo("  ", 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El nombre de la carga es necesario");
    }

    @Test
    @DisplayName("rechaza una cantidad de cero")
    void shouldRejectZeroQuantity() {
        assertThatThrownBy(() -> new Cargo("Huevo de dragón", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La cantidad debe ser mayor que 0");
    }

    @Test
    @DisplayName("rechaza una cantidad negativa")
    void shouldRejectNegativeQuantity() {
        assertThatThrownBy(() -> new Cargo("Huevo de dragón", -3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La cantidad debe ser mayor que 0");
    }


}
