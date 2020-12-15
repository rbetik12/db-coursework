package io.rbetik12.eengine.entity;

import io.rbetik12.eengine.entity.composite.ActorCurrencyId;

import javax.persistence.*;

@Entity
@Table(name = "actor_currency")
@IdClass(ActorCurrencyId.class)
public class ActorCurrency {

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "id")
    private Actor actor;

    @Id
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @Column
    private int amount;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
