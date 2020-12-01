package io.rbetik12.eengine.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item_type")
public class ItemType {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_type", referencedColumnName = "id")
    private ItemType parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ItemType> children;

    public ItemType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getParent() {
        return parent;
    }

    public void setParent(ItemType parent) {
        this.parent = parent;
    }

    public List<ItemType> getChildren() {
        return children;
    }

    public void setChildren(List<ItemType> children) {
        this.children = children;
    }
}
