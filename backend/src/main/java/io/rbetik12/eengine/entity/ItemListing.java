package io.rbetik12.eengine.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_listing")
public class ItemListing implements Serializable {

    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "listing_id")
    private Listing listingId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemId;

    @Column
    private long price;

    @Column
    private long amount;

    @ManyToOne
    @JoinColumn(name = "currency")
    private Currency currency;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "buyer_actor_id")
    private Actor buyerActor;

    @ManyToOne
    @JoinColumn(name = "buyer_clan_id")
    private Clan buyerClan;

    public Listing getListingId() {
        return listingId;
    }

    public void setListingId(Listing listingId) {
        this.listingId = listingId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Actor getBuyerActor() {
        return buyerActor;
    }

    public void setBuyerActor(Actor buyerActor) {
        this.buyerActor = buyerActor;
    }

    public Clan getBuyerClan() {
        return buyerClan;
    }

    public void setBuyerClan(Clan buyerClan) {
        this.buyerClan = buyerClan;
    }
}
