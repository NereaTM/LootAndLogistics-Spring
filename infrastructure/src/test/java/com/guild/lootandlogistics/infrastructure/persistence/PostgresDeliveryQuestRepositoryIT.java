package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.*;
import com.guild.lootandlogistics.domain.repository.DeliveryQuestRepository;
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

import java.util.List;
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

    // Filtro con null: equivale al listado completo
    private final DeliveryQuestRepository.DeliveryQuestFilter noFilter =
            new DeliveryQuestRepository.DeliveryQuestFilter(null, null, null);

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

    @Test
    @DisplayName("Devuelve todos los encargos guardados")
    void findAllReturnsAllSavedQuests() {
        Location origin = new Location(null, "Calle Mayor", "Valle Norte");
        Location destination = new Location("Torre del Mago", "Camino Alto", "Montes Grises");
        Cargo cargo = new Cargo("Pociones", 3);
        Money reward = new Money(50, Currency.GOLD);

        repository.save(DeliveryQuest.create("Llevar pociones", origin, destination, cargo, reward, DangerLevel.LOW));
        repository.save(DeliveryQuest.create("Escoltar al bardo", origin, destination, cargo, reward, DangerLevel.LOW));
        entityManager.flush();
        entityManager.clear();

        List<DeliveryQuest> found = repository.findAll(noFilter);

        assertThat(found)
                .extracting(quest -> quest.getTitle())
                .containsExactlyInAnyOrder("Llevar pociones", "Escoltar al bardo");
    }

    @Test
    @DisplayName("Con filtro, devuelve solo los encargos que cumplen todos los criterios")
    void findAllReturnsOnlyMatchingQuests() {
        Location origin = new Location(null, "Calle Mayor", "Valle Norte");
        Location destination = new Location("Torre del Mago", "Camino Alto", "Montes Grises");

        // cumple los tres criterios
        repository.save(DeliveryQuest.create("Llevar pociones", origin, destination,
                new Cargo("Pociones", 3), new Money(50, Currency.GOLD), DangerLevel.LOW));
        // misma carga, pero pagado en plata
        repository.save(DeliveryQuest.create("Pociones baratas", origin, destination,
                new Cargo("Pociones", 3), new Money(50, Currency.SILVER), DangerLevel.LOW));
        // pagado en oro, pero otra carga
        repository.save(DeliveryQuest.create("Escoltar al bardo", origin, destination,
                new Cargo("Laúd", 1), new Money(50, Currency.GOLD), DangerLevel.LOW));
        entityManager.flush();
        entityManager.clear();

        List<DeliveryQuest> found = repository.findAll(
                new DeliveryQuestRepository.DeliveryQuestFilter(QuestStatus.AVAILABLE, "Pociones", Currency.GOLD));

        assertThat(found)
                .extracting(quest -> quest.getTitle())
                .containsExactly("Llevar pociones");
    }

    @Test
    @DisplayName("Si no hay encargos devuelve una lista vacía")
    void findAllReturnsEmptyListWhenNoQuests() {
        List<DeliveryQuest> found = repository.findAll(noFilter);

        assertThat(found).isEmpty();
    }
}