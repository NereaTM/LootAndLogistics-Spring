package com.guild.lootandlogistics.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Location")
public class LocationTest {

    @Test
    @DisplayName("Se crea con todo")
    void shouldCreateLocationWithName(){
        Location location = new Location("Gremio de los Ventureros", "Muelles del Puerto de Rómenna", "Númenor");

        assertThat(location.name()).isEqualTo("Gremio de los Ventureros");
        assertThat(location.street()).isEqualTo("Muelles del Puerto de Rómenna");
        assertThat(location.region()).isEqualTo("Númenor");
    }

    @Test
    @DisplayName("Se crea sin nombre")
    void shouldCreateLocationWithoutName(){
        Location location = new Location(null, "Muelles del Puerto de Rómenna", "Númenor");

        assertThat(location.name()).isNull();
        assertThat(location.street()).isEqualTo("Muelles del Puerto de Rómenna");
        assertThat(location.region()).isEqualTo("Númenor");
    }

    @Test
    @DisplayName("Rechaza un nombre en blanco")
    void shouldRejectBlankName() {
        assertThatThrownBy(() -> new Location("  ", "Muelles del Puerto de Rómenna", "Númenor"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El nombre del lugar no puede estar en blanco");
    }

    @Test
    @DisplayName("Rechaza una calle nula")
    void shouldRejectNullStreet() {
        assertThatThrownBy(() -> new Location("Gremio de los Ventureros", null, "Númenor"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La calle es obligatoria");
    }

    @Test
    @DisplayName("Rechaza una calle en blanco")
    void shouldRejectBlankStreet() {
        assertThatThrownBy(() -> new Location("Gremio de los Ventureros", "  ", "Númenor"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La calle es obligatoria");
    }

    @Test
    @DisplayName("Rechaza una región nula")
    void shouldRejectNullRegion() {
        assertThatThrownBy(() -> new Location("Gremio de los Ventureros", "Muelles del Puerto de Rómenna", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La region es obligatoria");
    }

    @Test
    @DisplayName("Rechaza una región en blanco")
    void shouldRejectBlankRegion() {
        assertThatThrownBy(() -> new Location("Gremio de los Ventureros", "Muelles del Puerto de Rómenna", "  "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La region es obligatoria");
    }

}
