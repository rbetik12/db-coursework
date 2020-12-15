package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ClanCurrency;
import io.rbetik12.eengine.entity.composite.ClanCurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanCurrencyRepository extends JpaRepository<ClanCurrency, ClanCurrencyId> {
}
