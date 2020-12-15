package io.rbetik12.eengine.entity;

import io.rbetik12.eengine.entity.composite.ClanCurrencyId;

import javax.persistence.*;

@Entity
@Table
@IdClass(ClanCurrencyId.class)
public class ClanCurrency {

    @Id
    @ManyToOne
    @JoinColumn(name = "clan_id", referencedColumnName = "id")
    private Clan clan;

    @Id
    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @Column
    private int amount;

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
