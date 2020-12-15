package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ActorCurrency;
import io.rbetik12.eengine.entity.composite.ActorCurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorCurrencyRepository extends JpaRepository<ActorCurrency, ActorCurrencyId> {
}
