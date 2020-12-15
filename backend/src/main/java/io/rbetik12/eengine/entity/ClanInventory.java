package io.rbetik12.eengine.entity;

import io.rbetik12.eengine.entity.composite.ClanInventoryId;

import javax.persistence.*;

@Entity
@Table
@IdClass(ClanInventoryId.class)
public class ClanInventory {

    @Id
    @ManyToOne
    @JoinColumn(name = "clan_id", referencedColumnName = "id")
    private Clan clan;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @Column
    private int amount;

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
