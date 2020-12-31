package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ClanCurrency;
import io.rbetik12.eengine.entity.composite.ClanCurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClanCurrencyRepository extends JpaRepository<ClanCurrency, ClanCurrencyId> {

    List<ClanCurrency> getAllByClan_IdAndCurrency_Id(long clanId, long currencyId);
}
