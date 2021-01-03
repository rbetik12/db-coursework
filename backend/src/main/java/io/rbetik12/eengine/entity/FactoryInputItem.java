package io.rbetik12.eengine.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factory_input_item")
public class FactoryInputItem implements Serializable {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne
  @JoinColumn(name = "next_item", referencedColumnName = "id")
  private FactoryInputItem next;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public FactoryInputItem getNext() {
    return next;
  }

  public void setNext(FactoryInputItem next) {
    this.next = next;
  }
}
