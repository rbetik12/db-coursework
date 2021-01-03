package io.rbetik12.eengine.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Item")
public class Item implements Serializable {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  @JoinColumn(name = "type")
  private ItemType type;

  @Column
  private String name;

  @Column
  private String description;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
