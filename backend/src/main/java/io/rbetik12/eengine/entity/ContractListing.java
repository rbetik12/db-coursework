package io.rbetik12.eengine.entity;

import io.rbetik12.eengine.entity.composite.ContractListingId;

import javax.persistence.*;

@Entity
@IdClass(ContractListingId.class)
public class ContractListing {

    @Id
    @Column
    private int contractId;

    @Id
    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;

    @Column
    private String type;

    @Column
    private String executorType;

    @ManyToOne
    @JoinColumn(name = "executor_actor_id", referencedColumnName = "id")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "executor_clan_id", referencedColumnName = "id")
    private Clan clan;

    @Column
    private int reward;

    @ManyToOne
    @JoinColumn(name = "currency")
    private Currency currency;

    @Column
    private int ratingAmount;

    @Column
    private String status;

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExecutorType() {
        return executorType;
    }

    public void setExecutorType(String executorType) {
        this.executorType = executorType;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getRatingAmount() {
        return ratingAmount;
    }

    public void setRatingAmount(int ratingAmount) {
        this.ratingAmount = ratingAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
