package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.Cargo;
import com.guild.lootandlogistics.domain.entity.Currency;
import com.guild.lootandlogistics.domain.entity.DangerLevel;
import com.guild.lootandlogistics.domain.entity.DeliveryQuest;
import com.guild.lootandlogistics.domain.entity.Location;
import com.guild.lootandlogistics.domain.entity.Money;
import com.guild.lootandlogistics.domain.entity.QuestId;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@DisplayName("Guardar y leer encargos en Postgres")
class PostgresDeliveryQuestRepositoryIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:18");

    @Autowired
    private DeliveryQuestJpaRepository jpaRepository;

    @Autowired
    private EntityManager entityManager;

    private PostgresDeliveryQuestRepository repository;

    @BeforeEach
    void setUp() {
        repository = new PostgresDeliveryQuestRepository(
                jpaRepository, Mappers.getMapper(DeliveryQuestEntityMapper.class));
    }

    @Test
    @DisplayName("Un encargo guardado se puede recuperar igual que entró")
    void savedQuestCanBeFound() {
        DeliveryQuest quest = DeliveryQuest.create(
                "Llevar pociones",
                new Location(null, "Calle Mayor", "Valle Norte"),
                new Location("Torre del Mago", "Camino Alto", "Montes Grises"),
                new Cargo("Pociones", 3),
                new Money(50, Currency.GOLD),
                DangerLevel.LOW);

        repository.save(quest);
        entityManager.flush();
        entityManager.clear();

        Optional<DeliveryQuest> found = repository.findById(quest.getId());

        assertThat(found).isPresent();
        assertThat(found.get()).usingRecursiveComparison().isEqualTo(quest);
    }

    @Test
    @DisplayName("Si el encargo no existe devuelve vacío")
    void unknownIdReturnsEmpty() {
        Optional<DeliveryQuest> found = repository.findById(QuestId.generate());

        assertThat(found).isEmpty();
    }
}