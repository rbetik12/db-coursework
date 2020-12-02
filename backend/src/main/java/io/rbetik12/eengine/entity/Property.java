package io.rbetik12.eengine.entity;

import javax.persistence.*;

@Entity
@Table(name = "Property")
public class Property {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "clan_owner_id")
    private Clan clan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
