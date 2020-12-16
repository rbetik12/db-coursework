package io.rbetik12.eengine.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FactoryOwner {

    @Id
    private int relId;

    @ManyToOne
    @JoinColumn(name = "clan_id", referencedColumnName = "id")
    private Clan clan;

    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "id")
    private Actor actor;

    @ManyToOne
    @JoinColumn(name = "factory_id", referencedColumnName = "id")
    private Factory factory;

    public int getRelId() {
        return relId;
    }

    public void setRelId(int relId) {
        this.relId = relId;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
