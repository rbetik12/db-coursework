package io.rbetik12.eengine.entity;


import javax.persistence.*;

@Entity
@Table(name = "Actor")
public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne()
  @JoinColumn(name = "clan_id")
  private Clan clan;

  @Column
  private String type;

  @Column
  private long rating;

  @Column
  private String name;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Clan getClan() {
    return clan;
  }

  public void setClan(Clan clan) {
    this.clan = clan;
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


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
