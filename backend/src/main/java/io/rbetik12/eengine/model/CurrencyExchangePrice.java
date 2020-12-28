package io.rbetik12.eengine.model;

import io.rbetik12.eengine.entity.Currency;

import java.io.Serializable;
import java.util.Objects;

public class CurrencyExchangePrice implements Serializable {

    private Currency currency1;
    private Currency currency2;
    private float price;

    public Currency getCurrency1() {
        return currency1;
    }

    public void setCurrency1(Currency currency1) {
        this.currency1 = currency1;
    }

    public Currency getCurrency2() {
        return currency2;
    }

    public void setCurrency2(Currency currency2) {
        this.currency2 = currency2;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public CurrencyExchangePrice(Currency currency1, Currency currency2, float price) {
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.price = price;
    }

    public CurrencyExchangePrice() {
    }

    @Override
    public String toString() {
        return "CurrencyExchangePrice{" +
                "currency1=" + currency1 +
                ", currency2=" + currency2 +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyExchangePrice that = (CurrencyExchangePrice) o;
        return Float.compare(that.price, price) == 0 &&
                Objects.equals(currency1, that.currency1) &&
                Objects.equals(currency2, that.currency2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency1, currency2, price);
    }
}
