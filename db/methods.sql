create or replace function get_item_id_by_name(itemName varchar)
returns int
language plpgsql
as
    $$
    declare itemId int;
    begin
        select id into itemId from Item where Item.name = itemName;
        return itemId;
    end;
    $$;

create or replace function get_currency_id_by_name(currencyName varchar)
returns int
language plpgsql
as
    $$
    declare currencyId int;
    begin
        select id into currencyId from Currency where Currency.name = currencyName;
        return currencyId;
    end;
    $$;

create or replace function get_itemtype_id_by_name(itemTypeName varchar)
returns int
language plpgsql
as
    $$
    declare itemTypeId int;
    begin
        select id into itemTypeId from ItemType where ItemType.name = itemTypeName;
        return itemTypeId;
    end;
    $$;

create or replace function get_clan_id_by_name(clanName varchar)
returns int
language plpgsql
as
    $$
    declare clanId int;
    begin
        select id into clanId from Clan where Clan.name = clanName;
        return clanId;
    end;
    $$;

create or replace function get_reward_id_by_name(rewardName varchar)
returns int
language plpgsql
as
    $$
    declare rewardId int;
    begin
        select id into rewardId from Reward where Reward.name = rewardName;
        return rewardId;
    end;
    $$;

create or replace function get_region_id_by_name(regionName varchar)
returns int
language plpgsql
as
    $$
    declare regionId int;
    begin
        select id into regionId from region where region.name = regionName;
        return regionId;
    end;
    $$;

create or replace function get_actor_id_by_name(actorName varchar)
returns int
language plpgsql
as
    $$
    declare actorId int;
    begin
        select id into actorId from actor where actor.name = actorName;
        return actorId;
    end;
    $$;
    
create or replace function create_new_item_listing_for_clan(clanId int, itemId int,
                                                       itemPrice int, itemAmount int, currencyId int,
                                                       listingDescription text)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listings (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into itemlisting (listing_id, item_id, price, amount, currency)
    values ((select listingId from insertListing), itemId, itemPrice, itemAmount, currencyId);
end;
$$;

create or replace function create_new_item_listing_for_actor(actorId int, itemId int, itemPrice int,
                                                        itemAmount int, currencyId int, listingDescription text)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listings (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into itemlisting (listing_id, item_id, price, amount, currency)
    values ((select listingId from insertListing), itemId, itemPrice, itemAmount, currencyId);
end;
$$;

create or replace function create_new_contract_for_clan(clanId int, contractType text,
                                                           contractReward int, currencyId int, ratingAmount int,
                                                           listingDescription text)
    returns void
    language plpgsql
as
$$
begin
    with insertListing as (
        insert into listings (seller, clan_id, description) values ('Clan', clanId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into contract(type, reward, currency, rating_amount, listing_id)
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
        insert into listings (seller, author_id, description) values ('Actor', actorId, listingDescription)
            returning listing_id as listingId
    )
    insert
    into contract(type, reward, currency, rating_amount, listing_id)
    values (contractType::contract_type, contractReward, currencyId, ratingAmount,
            (select listingId from insertListing));
end;
$$;


