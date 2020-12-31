package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ItemListing;
import io.rbetik12.eengine.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemListingRepository extends JpaRepository<ItemListing, Listing> {

    @Query(value = "select count(*) from buy_item_as_actor(?1, ?2)", nativeQuery = true)
    int buyItemAsActor(int actorId, int listingId);

    @Query(value = "select count(*) from buy_item_as_clan(?1, ?2)", nativeQuery = true)
    int buyItemAsClan(int clanId, int listingId);
}
