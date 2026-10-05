package com.guild.lootandlogistics.domain.entity;

/**
 * El tablón del gremio, con el encargo de transporte
 */
public class DeliveryQuest {

    private final QuestId id;
    private final String title;
    private final Location origin;
    private final Location destination;
    private final Cargo cargo;
    private final Money reward;
    private final DangerLevel dangerLevel;
    private QuestStatus status;

    private DeliveryQuest(
            QuestId id, String title,
            Location origin, Location destination,
            Cargo cargo, Money reward,
            DangerLevel dangerLevel,QuestStatus status ) {
        this.id = id;
        this.title = title;
        this.origin = origin;
        this.destination = destination;
        this.cargo = cargo;
        this.reward = reward;
        this.dangerLevel = dangerLevel;
        this.status = status;
    }

    public static DeliveryQuest create(
            String title,
            Location origin, Location destination,
            Cargo cargo, Money reward,
            DangerLevel dangerLevel){

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título del encargo es obligatorio");
        }

        if (origin == null) {
            throw new IllegalArgumentException("El origen del encargo es obligatorio");
        }

        if (destination == null) {
            throw new IllegalArgumentException("El destino del encargo es obligatorio");
        }

        if (origin.equals(destination)) {
            throw new IllegalArgumentException("El origen y el destino del encargo no pueden ser el mismo");
        }

        if (cargo == null) {
            throw new IllegalArgumentException("La carga del encargo es obligatoria");
        }

        if (reward == null || reward.amount() <=0 ) {
            throw new IllegalArgumentException("La recompensa es obligatoria y mayor que 0");
        }

        if (dangerLevel == null) {
            throw new IllegalArgumentException("El peligro del encargo es obligatorio");
        }

        return new DeliveryQuest(QuestId.generate(), title, origin, destination,
                cargo, reward, dangerLevel, QuestStatus.AVAILABLE);
    }

    public QuestId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Money getReward() {
        return reward;
    }

    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }

    public QuestStatus getStatus() {
        return status;
    }
}
