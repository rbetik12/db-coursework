package io.rbetik12.eengine.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "listing")
public class Listing implements Serializable {

    @Id
    @GeneratedValue
    private long listingId;

    @Column
    private String seller;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "clan_id")
    private Clan clan;

    @Column
    private String description;

    public long getListingId() {
        return listingId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
