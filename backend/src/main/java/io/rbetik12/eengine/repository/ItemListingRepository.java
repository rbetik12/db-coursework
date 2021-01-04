package io.rbetik12.eengine.repository;

import io.rbetik12.eengine.entity.ItemListing;
import io.rbetik12.eengine.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemListingRepository extends JpaRepository<ItemListing, Listing> {

    @Query(value = "select count(*) from buy_item_as_actor(?1, ?2)", nativeQuery = true)
    int buyItemAsActor(int actorId, int listingId);

    @Query(value = "select count(*) from buy_item_as_clan(?1, ?2)", nativeQuery = true)
    int buyItemAsClan(int clanId, int listingId);

    @Query(value = "select count(*) from create_new_item_listing_for_actor(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    int createItemListingAsActor(int actorId, int itemId, int itemPrice, int itemAmount,
                                 int currencyId,
                                 String description);

    List<ItemListing> getAllByItem_IdAndCurrency_Id(long itemId, long currencyId);
}
