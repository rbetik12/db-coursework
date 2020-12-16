package io.rbetik12.eengine.entity;

import javax.persistence.*;

@Entity
public class CurrencyListing {

    @Id
    @Column
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "listing_id", referencedColumnName = "listing_id")
    private Listing listing;

    @JoinColumn(name = "buyer_actor_id", referencedColumnName = "id")
    @ManyToOne
    private Actor buyerActor;

    @JoinColumn(name = "buyer_clan_id", referencedColumnName = "id")
    @ManyToOne
    private Clan buyerClan;

    @ManyToOne
    @JoinColumn(name = "currency_for_sell_id", referencedColumnName = "id")
    private Currency currencyForSell;

    @ManyToOne
    @JoinColumn(name = "currency_for_buy_id", referencedColumnName = "id")
    private Currency currencyForBuy;

    @Column
    private String status;

    @Column
    private int sellAmount;

    @Column
    private int buyAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Actor getBuyerActor() {
        return buyerActor;
    }

    public void setBuyerActor(Actor buyerActor) {
        this.buyerActor = buyerActor;
    }

    public Clan getBuyerClan() {
        return buyerClan;
    }

    public void setBuyerClan(Clan buyerClan) {
        this.buyerClan = buyerClan;
    }

    public Currency getCurrencyForSell() {
        return currencyForSell;
    }

    public void setCurrencyForSell(Currency currencyForSell) {
        this.currencyForSell = currencyForSell;
    }

    public Currency getCurrencyForBuy() {
        return currencyForBuy;
    }

    public void setCurrencyForBuy(Currency currencyForBuy) {
        this.currencyForBuy = currencyForBuy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }

    public int getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }
}
