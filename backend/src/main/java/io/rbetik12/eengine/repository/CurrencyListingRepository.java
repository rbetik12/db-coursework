package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.CurrencyListing;
import io.rbetik12.eengine.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurrencyListingRepository extends JpaRepository<CurrencyListing, Listing> {

    @Query(value = "select count(*) from create_new_currency_listing_for_actor(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    int createForActor(int actorId, int currencyForSellId, int currencyToBuyId, int currencyForSellAmount,
                       int currencyToBuyAmount, String description);
}
