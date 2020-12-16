package io.rbetik12.eengine.entity;

import javax.persistence.*;

@Entity
public class PropertyListing {

    @Id
    @Column
    private int id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "listing_id", referencedColumnName = "listing_id")
    private Listing listing;

    @JoinColumn(name = "buyer_actor_id", referencedColumnName = "id")
    @ManyToOne
    private Actor buyerActor;

    @JoinColumn(name = "buyer_clan_id", referencedColumnName = "id")
    @ManyToOne
    private Clan buyerClan;

    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @ManyToOne
    private Property property;

    @Column
    private String status;

    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @ManyToOne
    private Currency currency;

    @Column
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
