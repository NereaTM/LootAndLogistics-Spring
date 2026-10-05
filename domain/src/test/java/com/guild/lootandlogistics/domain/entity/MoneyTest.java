package com.guild.lootandlogistics.domain.entity;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Money")
public class MoneyTest {

    @Test
    @DisplayName("Un Money válido guarda su cantidad y su moneda")
    void shouldCreateRewardOfGoldCoins() {
        Money reward = new Money(100, Currency.GOLD);

        assertThat(reward.amount()).isEqualTo(100);
        assertThat(reward.currency()).isEqualTo(Currency.GOLD);
    }

    @Test
    @DisplayName("No se puede crear dinero sin indicar la moneda")
    void shouldNotAcceptCoinsFromUnknownKingdom() {
        assertThatThrownBy(() -> new Money(15, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La divisa es necesaria");
    }

}
