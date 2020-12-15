package io.rbetik12.eengine.entity.composite;

import java.io.Serializable;
import java.util.Objects;

public class ClanInventoryId implements Serializable {

    private long clan;

    private long item;

    public ClanInventoryId() {
    }

    public ClanInventoryId(long clan, long item) {
        this.clan = clan;
        this.item = item;
    }

    public long getClan() {
        return clan;
    }

    public void setClan(long clan) {
        this.clan = clan;
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
        ClanInventoryId that = (ClanInventoryId) o;
        return clan == that.clan &&
                item == that.item;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clan, item);
    }
}
