package io.rbetik12.eengine.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Clan")
public class Clan {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column
    private String type;

    @Column
    private long rating;

    public Clan() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Region getRegion() {
        return region;
    }

    public void setRegion(Region regionId) {
        this.region = regionId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

//    public List<Actor> getActors() {
//        return actors;
//    }
//
//    public void setActors(List<Actor> actors) {
//        this.actors = actors;
//    }
}
