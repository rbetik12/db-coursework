--------------------------------------------------------Item listing----------------------------------------------------
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

----------------------------------------------------Property listing----------------------------------------------------
create or replace function create_new_property_listing_for_actor(actorId int, propertyId int,
                                            listingDescription varchar, currencyId int,
                                                      propertyPrice int)
    returns void
    language plpgsql
as
$$
    declare propertyAmount int;
begin
    propertyAmount = (select count(*) from property where owner_id = actorId and id = propertyId);
    if (propertyAmount is null or propertyAmount <= 0) then
        return;
    end if;
    with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into property_listing (listing_id, property_id, price, currency_id)
    values ((select listingId from insertListing), propertyId, propertyPrice, currencyId);
end;
$$;

create or replace function create_new_property_listing_for_clan(clanId int, propertyId int, listingDescription varchar,
                                                        currencyId int,
                                                      propertyPrice int)
    returns void
    language plpgsql
as
$$
    declare propertyAmount int;
begin
    propertyAmount = (select count(*) from property where clan_owner_id = clanId and id = propertyId);
    if (propertyAmount is null or propertyAmount <= 0) then
        return;
    end if;
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into property_listing (listing_id, property_id, price, currency_id)
    values ((select listingId from insertListing), propertyId, propertyPrice, currencyId);
end;
$$;

----------------------------------------------Currency listing----------------------------------------------------------

create or replace function create_new_currency_listing_for_actor(actorId int, currencyForSellId int,
currencyToBuyId int, currencyForSellAmount int, currencyToBuyAmount int, listingDescription varchar)
    returns void
    language plpgsql
as
$$
    declare currencyAmount int;
begin
    currencyAmount = (select amount from actor_currency where actor_id = actorId and currency_id = currencyForSellId);
    if (currencyAmount is null or currencyAmount < currencyForSellAmount) then
        return;
    end if;
    with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into currency_listing (listing_id, currency_for_sell_id, currency_for_buy_id, sell_amount, buy_amount)
    values ((select listingId from insertListing), currencyForSellId, currencyToBuyId,
                                                                            currencyForSellAmount, currencyToBuyAmount);
    update actor_currency set amount = currencyAmount - currencyForSellAmount where actor_id = actorId
                                                                                    and currency_id = currencyForSellId;
end;
$$;

create or replace function create_new_currency_listing_for_clan(clanId int, currencyForSellId int,
currencyToBuyId int, currencyForSellAmount int, currencyToBuyAmount int, listingDescription varchar)
    returns void
    language plpgsql
as
$$
declare currencyAmount int;
begin
    currencyAmount = (select amount from clan_currency where clan_id = clanId and currency_id = currencyForSellId);
    if (currencyAmount is null or currencyAmount < currencyForSellAmount) then
        return;
    end if;
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into currency_listing (listing_id, currency_for_sell_id, currency_for_buy_id, sell_amount, buy_amount)
    values ((select listingId from insertListing), currencyForSellId, currencyToBuyId, currencyForSellAmount,
                                                                                            currencyToBuyAmount);
    update clan_currency set amount = currencyAmount - currencyForSellAmount where clan_id = clanId
                                                                                and currency_id = currencyForSellId;
end;
$$;

------------------------------------------------------------------Factory listing---------------------------------------

create or replace function create_new_factory_listing_for_actor(actorId int, factoryId int, currencyId int,
                                                                factoryPrice int, listingDescription varchar)
    returns void
    language plpgsql
as
$$
declare factoryAmount int;
begin
    factoryAmount = (select count(*) from factory_owner where actor_id = actorId and factory_id = factoryId);
    if (factoryAmount is null or factoryAmount <= 0) then
        return;
    end if;
    with insertListing as (
        insert into listing (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into factory_listing (listing_id, factory_id, currency_id, price)
    values ((select listingId from insertListing), factoryId, currencyId, factoryPrice);

end;
$$;

create or replace function create_new_factory_listing_for_clan(clanId int, factoryId int, currencyId int,
                                    factoryPrice int, listingDescription varchar)
    returns void
    language plpgsql
as
$$
declare factoryAmount int;
begin
    factoryAmount = (select count(*) from factory_owner where clan_id = clanId and factory_id = factoryId);
    if (factoryAmount is null or factoryAmount <= 0) then
        return;
    end if;
    with insertListing as (
        insert into listing (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into factory_listing (listing_id, factory_id, currency_id, price)
    values ((select listingId from insertListing), factoryId, currencyId, factoryPrice);
end;
$$;

------------------------------------------------New clan creation-------------------------------------------------------
create or replace function create_new_clan(creatorId int, clanName varchar, regionId int, clanType varchar) returns void
language plpgsql
as
    $$
    declare actorClanId int;
    begin
        actorClanId = (select clan_id from actor where id = creatorId);
        if actorClanId is null then
            with new_clan as (
                insert into clan (name, region_id, type) values (clanName, regionId, clanType::clan_type)
                returning id as clanId
            )
            update actor set clan_id = (select clanId from new_clan), clan_role = 'Leader' where id = creatorId;
        else
            raise notice 'Actor already in clan!';
        end if;
    end;
$$;

----------------------------------------------------------Buy items-----------------------------------------------------

create or replace function buy_item_as_actor(actorId int, listingId int) returns void
language plpgsql
as
    $$
    declare listingInfo listing%rowtype;
    declare itemListingInfo item_listing%rowtype;
    declare actorCurrencyAmount int;
    begin
        select * into listingInfo from listing where author_id = actorId and listing_id = listingId limit 1;
        select * into itemListingInfo from item_listing where listing_id = listingId limit 1;
        if (itemListingInfo.status = 'Closed'::listing_status) then
            return;
        end if;

        actorCurrencyAmount = (select amount from actor_currency where actor_id = actorId
        and currency_id = itemListingInfo.currency);

        if (actorCurrencyAmount >= itemListingInfo.price) then
            if ((select count(*) from actor_inventory where actor_id = actorId
                and item_id = itemListingInfo.item_id) != 0) then
                update actor_inventory set amount = amount + itemListingInfo.amount
                    where actor_id = actorId and item_id = itemListingInfo.item_id;
            else
                insert into actor_inventory(actor_id, item_id, amount)
                    values (actorId, itemListingInfo.item_id, itemListingInfo.amount);
            end if;
            update actor_currency set amount = amount - itemListingInfo.price where actor_id = actorId
                                                                        and currency_id = itemListingInfo.currency;
            update item_listing set status = 'Closed' where listing_id = listingId;
        end if;
    end;
    $$;

create or replace function buy_item_as_clan(clanId int, listingId int) returns void
language plpgsql
as
    $$
    declare listingInfo listing%rowtype;
    declare itemListingInfo item_listing%rowtype;
    declare clanCurrencyAmount int;
    begin
        select * into listingInfo from listing where clan_id = clanId and listing_id = listingId limit 1;
        select * into itemListingInfo from item_listing where listing_id = listingId limit 1;
        if (itemListingInfo.status = 'Closed'::listing_status) then
            return;
        end if;

        clanCurrencyAmount = (select amount from clan_currency where clan_id = clanId
        and currency_id = itemListingInfo.currency);

        if (clanCurrencyAmount >= itemListingInfo.price) then
            if ((select count(*) from clan_inventory where clan_id = clanId
            and item_id = itemListingInfo.item_id) != 0) then
                update clan_inventory set amount = amount + itemListingInfo.amount
                where clan_id = clanId and item_id = itemListingInfo.item_id;
            else
                insert into clan_inventory(clan_id, item_id, amount)
                    values (clanId, itemListingInfo.item_id, itemListingInfo.amount);
            end if;
            update clan_currency set amount = amount - itemListingInfo.price
            where clan_id = clanId and currency_id = itemListingInfo.currency;
            update item_listing set status = 'Closed' where listing_id = listingId;
        end if;
    end;
    $$;

------------------------------------------Make item on factory----------------------------------------------------------

create or replace function make_item(actorId int, factoryId int, inputItemId int) returns void
language plpgsql
as
    $$
    declare factoryInputItemId int;
    declare factoryAmount int;
    declare actorItem int;
    declare outputActorItemId int;
    begin
        factoryAmount = (select count(*) from factory_owner where actor_id = actorId and factory_id = factoryId);
        if (factoryAmount is null or factoryAmount <= 0) then
            return;
        end if;
        factoryInputItemId = (select item.id from factory f
            join factory_input_item fii on f.input_items = fii.id
            join item on fii.item_id = item.id
            where f.id = factoryId
            );
        if (inputItemId != factoryInputItemId) then
            return;
        end if;

        actorItem = (select amount from actor_inventory where actor_id = actorId and item_id = inputItemId);
        if (actorItem is null) then
            return;
        end if;
        update actor_inventory set amount = amount - 1 where actor_id = actorId and item_id = inputItemId;

        outputActorItemId = (select amount from actor_inventory where actor_id = actorId
        and item_id = (select output_item from factory where id = factoryId));
        if (outputActorItemId is null) then
            insert into actor_inventory(actor_id, item_id, amount)
            values (actorId, (select output_item from factory where id = factoryId), 1);
        else
            update actor_inventory set amount = amount + 1 where actor_id = actorId
                                                             and item_id =
                                                                 (select output_item from factory where id = factoryId);
        end if;

    end;
    $$;

--------------------------------------------------------Item market price----------------------------------------------------

create or replace function count_item_market_price(itemId int, currencyId int) returns float
language plpgsql
as
    $$
    declare avgItemPrice float;
    begin
        avgItemPrice = (select avg(price) from item_listing where item_id = itemId and currency = currencyId);
        return avgItemPrice;
    end;
    $$;

-----------------------------------------------------------------Currency market price----------------------------------

create or replace function count_currency_market_price(currencyId1 int, currencyId2 int) returns float
language plpgsql
as
    $$
    declare currency1ToCurrency2Fraction float;
    begin
        currency1ToCurrency2Fraction = (select avg(currency_listing.buy_amount::float / sell_amount::float)
                                                                                        from currency_listing
        where currency_for_sell_id = currencyId1 and currency_for_buy_id = currencyId2);
        return currency1ToCurrency2Fraction;
    end;
    $$;

-----------------------------------------------------Factory market price-----------------------------------------------
create or replace function count_factory_market_price(factoryId int, currencyId int) returns float
language plpgsql
as
    $$
    declare avgFactoryPrice float;
    begin
        avgFactoryPrice = (select avg(price) from factory_listing where factory_id = factoryId
                                                                                        and currency_id = currencyId);
        return avgFactoryPrice;
    end;
    $$;

----------------------------------------------------------Property market price-----------------------------------------
create or replace function count_property_market_price(propertyId int, currencyId int) returns float
language plpgsql
as
    $$
    declare avgPropertyPrice float;
    begin
        avgPropertyPrice = (select avg(price) from property_listing where property_id = propertyId
                                                                                        and currency_id = currencyId);
        return avgPropertyPrice;
    end;
    $$;

----------------------------------------------------------------------------Currency buy--------------------------------
create or replace function buy_currency_as_actor(actorId int, listingId int)
    returns void
    language plpgsql
as
    $$
    declare listingInfo listing%rowtype;
    declare currencyListingInfo currency_listing%rowtype;
    declare actorCurrencyAmount int;
    begin
        select * into listingInfo from listing where listing_id = listingId;
        select * into currencyListingInfo from currency_listing where listing_id = listingId;
        if (currencyListingInfo.status = 'Closed'::listing_status) then
            return;
        end if;

        actorCurrencyAmount = (select amount from actor_currency where currency_id = currencyListingInfo.currency_for_buy_id
                                                                        and actor_id = actorId);
        if (actorCurrencyAmount is null or actorCurrencyAmount < currencyListingInfo.buy_amount) then
            return;
        end if;

        if ((select count(*) from actor_currency where actor_id = actorId and currency_id = currencyListingInfo.currency_for_sell_id) = 0) then
            insert into actor_currency(actor_id, currency_id, amount) values(actorId, currencyListingInfo.currency_for_sell_id, currencyListingInfo.sell_amount);
        else
            update actor_currency set amount = amount + currencyListingInfo.sell_amount where actor_id = actorId and currency_id = currencyListingInfo.currency_for_sell_id;
        end if;

        update actor_currency set amount = amount - currencyListingInfo.buy_amount where actor_id = actorId and currency_id = currencyListingInfo.currency_for_buy_id;

        if ((select count(*) from actor_currency where actor_id = listingInfo.author_id and currency_id = currencyListingInfo.currency_for_buy_id) = 0) then
            insert into actor_currency(actor_id, currency_id, amount) values(listingInfo.author_id, currencyListingInfo.currency_for_buy_id, currencyListingInfo.buy_amount);
        else
            update actor_currency set amount = amount + currencyListingInfo.buy_amount where actor_id = listingInfo.author_id and currency_id = currencyListingInfo.currency_for_buy_id;
        end if;

        update currency_listing set status = 'Closed'::listing_status where listing_id = listingId;
    end;
    $$;

create or replace function buy_currency_as_clan(clanId int, listingId int) returns void
language plpgsql
as
    $$
    declare listingInfo listing%rowtype;
    declare currencyListingInfo currency_listing%rowtype;
    declare clanCurrencyAmount int;
    begin
        select * into listingInfo from listing where listing_id = listingId;
        select * into currencyListingInfo from currency_listing where listing_id = listingId;
        if (currencyListingInfo.status = 'Closed'::listing_status) then
            return;
        end if;

        clanCurrencyAmount = (select amount from clan_currency where currency_id = currencyListingInfo.currency_for_buy_id
                                                                        and clan_id = clanId);
        if (clanCurrencyAmount is null or clanCurrencyAmount < currencyListingInfo.buy_amount) then
            return;
        end if;

        if ((select count(*) from clan_currency where clan_id = clanId and currency_id = currencyListingInfo.currency_for_sell_id) = 0) then
            insert into clan_currency(clan_id, currency_id, amount) values(clanId, currencyListingInfo.currency_for_sell_id, currencyListingInfo.sell_amount);
        else
            update clan_currency set amount = amount + currencyListingInfo.sell_amount where clan_id = clanId and currency_id = currencyListingInfo.currency_for_sell_id;
        end if;

        update clan_currency set amount = amount - currencyListingInfo.buy_amount where clan_id = clanId and currency_id = currencyListingInfo.currency_for_buy_id;

        if ((select count(*) from actor_currency where actor_id = listingInfo.author_id and currency_id = currencyListingInfo.currency_for_buy_id) = 0) then
            insert into actor_currency(actor_id, currency_id, amount) values(listingInfo.author_id, currencyListingInfo.currency_for_buy_id, currencyListingInfo.buy_amount);
        else
            update actor_currency set amount = amount + currencyListingInfo.buy_amount where actor_id = listingInfo.author_id and currency_id = currencyListingInfo.currency_for_buy_id;
        end if;

        update currency_listing set status = 'Closed'::listing_status where listing_id = listingId;
    end;
    $$;