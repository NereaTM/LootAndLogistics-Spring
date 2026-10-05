package com.guild.lootandlogistics.domain.entity;

/**
 * Define la cantidad del dinero del reino y sus atributos
 * @param amount cantidad de monedas
 * @param currency tipo de moneda
 */

public record Money(int amount, Currency currency) {
    public Money {

        if (currency == null) {
            throw new IllegalArgumentException("La divisa es necesaria");
        }
    }

}
