package com.guild.lootandlogistics.infrastructure.persistence;

import com.guild.lootandlogistics.domain.entity.Currency;
import com.guild.lootandlogistics.domain.entity.DangerLevel;
import com.guild.lootandlogistics.domain.entity.QuestStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "delivery_quests")
public class DeliveryQuestEntity {

    @Id
    private UUID id;

    @Column (nullable = false)
    private String title;

    @Column
    private String originName;

    @Column(nullable = false)
    private String originStreet;

    @Column(nullable = false)
    private String originRegion;

    @Column
    private String destinationName;

    @Column(nullable = false)
    private String destinationStreet;

    @Column(nullable = false)
    private String destinationRegion;

    @Column(nullable = false)
    private String cargoName;

    @Column(nullable = false)
    private int cargoQuantity;

    @Column(nullable = false)
    private int rewardAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency rewardCurrency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DangerLevel dangerLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestStatus status;

    protected DeliveryQuestEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginStreet() {
        return originStreet;
    }

    public void setOriginStreet(String originStreet) {
        this.originStreet = originStreet;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getOriginRegion() {
        return originRegion;
    }

    public void setOriginRegion(String originRegion) {
        this.originRegion = originRegion;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationStreet() {
        return destinationStreet;
    }

    public void setDestinationStreet(String destinationStreet) {
        this.destinationStreet = destinationStreet;
    }

    public String getDestinationRegion() {
        return destinationRegion;
    }

    public void setDestinationRegion(String destinationRegion) {
        this.destinationRegion = destinationRegion;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public int getCargoQuantity() {
        return cargoQuantity;
    }

    public void setCargoQuantity(int cargoQuantity) {
        this.cargoQuantity = cargoQuantity;
    }

    public int getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(int rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Currency getRewardCurrency() {
        return rewardCurrency;
    }

    public void setRewardCurrency(Currency rewardCurrency) {
        this.rewardCurrency = rewardCurrency;
    }

    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(DangerLevel dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }
}
