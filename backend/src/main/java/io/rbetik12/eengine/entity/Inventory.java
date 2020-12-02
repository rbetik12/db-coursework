package io.rbetik12.eengine.entity;


import javax.persistence.*;

@Entity
@Table(name = "Inventory")
public class Inventory {

    @EmbeddedId
    private InventoryId inventoryId;


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
