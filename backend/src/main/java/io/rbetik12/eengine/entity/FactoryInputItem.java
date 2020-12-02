package io.rbetik12.eengine.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "factory_input_item")
public class FactoryInputItem {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne
  @JoinColumn(name = "next_item", referencedColumnName = "id")
  private FactoryInputItem next;

  @OneToMany(mappedBy = "next", cascade = CascadeType.ALL)
  private List<FactoryInputItem> nextItems;

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

  public List<FactoryInputItem> getNextItems() {
    return nextItems;
  }

  public void setNextItems(List<FactoryInputItem> nextItems) {
    this.nextItems = nextItems;
  }
}
