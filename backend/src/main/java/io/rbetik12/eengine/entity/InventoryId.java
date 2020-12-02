package io.rbetik12.eengine.entity;

import java.io.Serializable;
import java.util.Objects;

public class InventoryId implements Serializable {

    private long actor;

    private long item;

    public InventoryId() {
    }

    public InventoryId(long actorId, long itemId) {
        this.actor = actorId;
        this.item = itemId;
    }

    public long getActor() {
        return actor;
    }

    public void setActor(long actor) {
        this.actor = actor;
    }

    public long getItem() {
        return item;
    }

    public void setItem(long item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryId that = (InventoryId) o;
        return actor == that.actor &&
                item == that.item;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actor, item);
    }
}
