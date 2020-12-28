package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Currency> findAllByName(String name);

    @Query(value = "select * from count_currency_market_price(?1, ?2)", nativeQuery = true)
    float countCurrencyMarketPrice(int currencyId1, int currencyId2);
}
