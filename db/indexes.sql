create index index_clan_rating on clan(rating);
create index index_actor_rating on actor(rating);

create index index_item_listing_item_id on item_listing using hash(item_id);
create index index_factory_listing_factory_id on factory_listing using hash(factory_id);
create index index_property_listing_id on property_listing using hash(property_id);
create index index_currency_listing_currency_for_sell_id on currency_listing using hash(currency_for_sell_id);
create index index_currency_listing_currency_for_buy_id on currency_listing using hash(currency_for_buy_id);