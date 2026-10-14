package com.guild.lootandlogistics.boot.config;

import com.guild.lootandlogistics.application.usecase.CreateDeliveryQuestUseCaseImpl;
import com.guild.lootandlogistics.application.usecase.FindAllDeliveryQuestsUseCaseImpl;
import com.guild.lootandlogistics.application.usecase.GetDeliveryQuestUseCaseImpl;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
import com.guild.lootandlogistics.domain.usecase.CreateDeliveryQuestUseCase;
import com.guild.lootandlogistics.domain.usecase.FindAllDeliveryQuestsUseCase;
import com.guild.lootandlogistics.domain.usecase.GetDeliveryQuestUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Registra los casos de uso, que no llevan anotaciones de Spring
 */
@Configuration(proxyBeanMethods = false)
public class UseCaseConfiguration {

    // Caso de uso - crear encargo
    @Bean
    public CreateDeliveryQuestUseCase createDeliveryQuestUseCase(DeliveryQuestRepository repository) {
        return new CreateDeliveryQuestUseCaseImpl(repository);
    }

    // Caso de uso - consultar encargo
    @Bean
    public GetDeliveryQuestUseCase getDeliveryQuestUseCase(DeliveryQuestRepository repository) {
        return new GetDeliveryQuestUseCaseImpl(repository);
    }

    // Caso de uso - listar
    @Bean
    public FindAllDeliveryQuestsUseCase findAllDeliveryQuestsUseCase(DeliveryQuestRepository repository) {
        return new FindAllDeliveryQuestsUseCaseImpl(repository);
    }
}