package io.rbetik12.eengine.entity;

import javax.persistence.*;

@Entity
@Table(name = "Factory")
public class Factory {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String type;

    @Column
    private double productivity;

    @ManyToOne
    @JoinColumn(name = "input_items")
    private FactoryInputItem inputItems;

    @ManyToOne
    @JoinColumn(name = "output_item")
    private Item outputItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getProductivity() {
        return productivity;
    }

    public void setProductivity(double productivity) {
        this.productivity = productivity;
    }

    public FactoryInputItem getInputItems() {
        return inputItems;
    }

    public void setInputItems(FactoryInputItem inputItems) {
        this.inputItems = inputItems;
    }

    public Item getOutputItem() {
        return outputItem;
    }

    public void setOutputItem(Item outputItem) {
        this.outputItem = outputItem;
    }
}
