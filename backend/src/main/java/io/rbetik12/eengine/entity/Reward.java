package io.rbetik12.eengine.entity;

import javax.persistence.*;

@Entity
public class Reward {
    @Id
    private int id;

    @Column
    private String type;

    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private Actor owner;

    @Column
    private String conditions;

    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getOwner() {
        return owner;
    }

    public void setOwner(Actor owner) {
        this.owner = owner;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
