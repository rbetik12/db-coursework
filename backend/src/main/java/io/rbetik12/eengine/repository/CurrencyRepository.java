package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Currency> findAllByName(String name);
}
