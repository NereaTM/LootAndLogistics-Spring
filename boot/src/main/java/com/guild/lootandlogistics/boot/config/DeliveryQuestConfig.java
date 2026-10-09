package com.guild.lootandlogistics.boot.config;

import com.guild.lootandlogistics.application.service.CreateDeliveryQuestService;
import com.guild.lootandlogistics.application.service.FindAllDeliveryQuestsService;
import com.guild.lootandlogistics.application.service.GetDeliveryQuestService;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
import com.guild.lootandlogistics.domain.usecase.CreateDeliveryQuestUseCase;
import com.guild.lootandlogistics.domain.usecase.FindAllDeliveryQuestsUseCase;
import com.guild.lootandlogistics.domain.usecase.GetDeliveryQuestUseCase;
import com.guild.lootandlogistics.infrastructure.persistence.DeliveryQuestEntityMapper;
import com.guild.lootandlogistics.infrastructure.persistence.DeliveryQuestJpaRepository;
import com.guild.lootandlogistics.infrastructure.persistence.PostgresDeliveryQuestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Crea los beans de los encargos que no llevan anotaciones de Spring
 */
@Configuration
public class DeliveryQuestConfig {

    // Repositorio de encargos
    @Bean
    public DeliveryQuestRepository deliveryQuestRepository(DeliveryQuestJpaRepository jpaRepository,
                                                           DeliveryQuestEntityMapper mapper) {
        return new PostgresDeliveryQuestRepository(jpaRepository, mapper);
    }

    // Caso de uso - crear encargo
    @Bean
    public CreateDeliveryQuestUseCase createDeliveryQuestUseCase(DeliveryQuestRepository repository) {
        return new CreateDeliveryQuestService(repository);
    }

    // Caso de uso - consultar encargo
    @Bean
    public GetDeliveryQuestUseCase getDeliveryQuestUseCase(DeliveryQuestRepository repository) {
        return new GetDeliveryQuestService(repository);
    }

    // Caso de uso - listar
    @Bean
    FindAllDeliveryQuestsUseCase findAllDeliveryQuestsUseCase(DeliveryQuestRepository repository) {
        return new FindAllDeliveryQuestsService(repository);
    }
}