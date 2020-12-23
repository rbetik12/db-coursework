package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ActorCurrency;
import io.rbetik12.eengine.entity.composite.ActorCurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorCurrencyRepository extends JpaRepository<ActorCurrency, ActorCurrencyId> {
    List<ActorCurrency> findAllByActor_Id(long actorId);
}
