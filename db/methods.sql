--------------------------------------------------------Item listing------------------------------------------------
create or replace function create_new_item_listing_for_actor(actorId int, itemId int, itemPrice int,
                                                        itemAmount int, currencyId int, listingDescription text)
    returns void
    language plpgsql
as
$$
    declare actorItemAmount int;
begin
   actorItemAmount = (select amount from actor_inventory where actor_id = actorId and item_id = itemId);
   if (actorItemAmount is null or actorItemAmount < itemAmount) then
       return;
   end if;
   update actor_inventory set amount = amount - itemAmount where actor_id = actorId and item_id = itemId;
   with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into item_listing (listing_id, item_id, price, amount, currency)
    values ((select listingId from insertListing), itemId, itemPrice, itemAmount, currencyId);
end;
$$;

create or replace function create_new_item_listing_for_clan(clanId int, itemId int,
                                                       itemPrice int, itemAmount int, currencyId int,
                                                       listingDescription text)
    returns void
    language plpgsql
as
$$
    declare clanItemAmount int;
begin
    clanItemAmount = (select amount from clan_inventory where clan_id = clanId and item_id = itemAmount);
    if (clanItemAmount is null or clanItemAmount < itemAmount) then
        return;
    end if;
    update clan_inventory set amount = amount - itemAmount where clan_id = clanId and item_id = itemId;
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into item_listing (listing_id, item_id, price, amount, currency)
    values ((select listingId from insertListing), itemId, itemPrice, itemAmount, currencyId);
end;
$$;

--------------------------------------------------------Contract listing------------------------------------------------

create or replace function create_new_contract_for_clan(clanId int, contractType text,
                                                           contractReward int, currencyId int, ratingAmount int,
                                                           listingDescription text)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into contract_listing(type, reward, currency, rating_amount, listing_id)
    values (contractType::contract_type, contractReward, currencyId, ratingAmount,
            (select listingId from insertListing));
end;
$$;

create or replace function create_new_contract_for_actor(actorId int, contractType text,
                                                            contractReward int, currencyId int, ratingAmount int,
                                                            listingDescription text)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into contract_listing(type, reward, currency, rating_amount, listing_id)
    values (contractType::contract_type, contractReward, currencyId, ratingAmount,
            (select listingId from insertListing));
end;
$$;

create function create_new_property_listing_for_actor(actorId int, propertyId int, listingDescription varchar, currencyId int,
                                                      propertyPrice int)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into property_listing (listing_id, property_id, price, currency_id)
    values ((select listingId from insertListing), propertyId, propertyPrice, currencyId);
end;
$$;

create function create_new_property_listing_for_clan(clanId int, propertyId int, listingDescription varchar, currencyId int,
                                                      propertyPrice int)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into property_listing (listing_id, property_id, price, currency_id)
    values ((select listingId from insertListing), propertyId, propertyPrice, currencyId);
end;
$$;

create or replace function create_new_property_listing_for_actor(actorId int, propertyId int, listingDescription varchar, currencyId int,
                                                      propertyPrice int)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into property_listing (listing_id, property_id, price, currency_id)
    values ((select listingId from insertListing), propertyId, propertyPrice, currencyId);
end;
$$;

create or replace function create_new_property_listing_for_clan(clanId int, propertyId int, listingDescription varchar, currencyId int,
                                                      propertyPrice int)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into property_listing (listing_id, property_id, price, currency_id)
    values ((select listingId from insertListing), propertyId, propertyPrice, currencyId);
end;
$$;

create or replace function create_new_currency_listing_for_actor(actorId int, currencyForSellId int,
currencyToBuyId int, currencyForSellAmount int, currencyToBuyAmount int, listingDescription varchar)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into currency_listing (listing_id, currency_for_sell_id, currency_for_buy_id, sell_amount, buy_amount)
    values ((select listingId from insertListing), currencyForSellId, currencyToBuyId, currencyForSellAmount, currencyToBuyAmount);
end;
$$;

create or replace function create_new_currency_listing_for_clan(clanId int, currencyForSellId int,
currencyToBuyId int, currencyForSellAmount int, currencyToBuyAmount int, listingDescription varchar)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into currency_listing (listing_id, currency_for_sell_id, currency_for_buy_id, sell_amount, buy_amount)
    values ((select listingId from insertListing), currencyForSellId, currencyToBuyId, currencyForSellAmount, currencyToBuyAmount);
end;
$$;

create or replace function create_new_factory_listing_for_actor(actorId int, factoryId int, currencyId int, factoryPrice int, listingDescription varchar)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into factory_listing (listing_id, factory_id, currency_id, price)
    values ((select listingId from insertListing), factoryId, currencyId, factoryPrice);
end;
$$;

create or replace function create_new_factory_listing_for_clan(clanId int, factoryId int, currencyId int, factoryPrice int, listingDescription varchar)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into factory_listing (listing_id, factory_id, currency_id, price)
    values ((select listingId from insertListing), factoryId, currencyId, factoryPrice);
end;
$$;


