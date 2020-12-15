package io.rbetik12.eengine.entity.composite;

import java.io.Serializable;

public class ClanCurrencyId implements Serializable {
    private long clan;
    private long currency;

    public ClanCurrencyId() {
    }

    public ClanCurrencyId(long clan, long currency) {
        this.clan = clan;
        this.currency = currency;
    }

    public long getClan() {
        return clan;
    }

    public void setClan(long clan) {
        this.clan = clan;
    }

    public long getCurrency() {
        return currency;
    }

    public void setCurrency(long currency) {
        this.currency = currency;
    }
}
