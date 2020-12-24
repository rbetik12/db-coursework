package io.rbetik12.eengine.entity;


import com.vladmihalcea.hibernate.type.array.EnumArrayType;
import io.rbetik12.eengine.entity.enums.ActorType;
import io.rbetik12.eengine.entity.type.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "clan_id")
  private Clan clan;

  @Column(columnDefinition = "actor_type")
  @Enumerated(EnumType.STRING)
  @Type(type = "pgsql_enum")
  private ActorType type;

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

  public ActorType getType() {
    return type;
  }

  public void setType(ActorType type) {
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
