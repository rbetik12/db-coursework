package io.rbetik12.eengine.entity;


import io.rbetik12.eengine.entity.composite.ActorInventoryId;

import javax.persistence.*;

@Entity
@Table(name = "actor_inventory")
@IdClass(ActorInventoryId.class)
public class ActorInventory {

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "id")
    private Actor actor;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @Column
    private long amount;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
