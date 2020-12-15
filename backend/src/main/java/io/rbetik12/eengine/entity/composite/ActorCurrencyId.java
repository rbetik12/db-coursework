package io.rbetik12.eengine.entity.composite;

import java.io.Serializable;
import java.util.Objects;

public class ActorCurrencyId implements Serializable {
    private long actor;
    private long currency;

    public ActorCurrencyId() {
    }

    public ActorCurrencyId(long actorId, long currencyId) {
        this.actor = actorId;
        this.currency = currencyId;
    }

    public long getActor() {
        return actor;
    }

    public void setActor(long actor) {
        this.actor = actor;
    }

    public long getCurrency() {
        return currency;
    }

    public void setCurrency(long currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorCurrencyId that = (ActorCurrencyId) o;
        return actor == that.actor &&
                currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actor, currency);
    }
}
