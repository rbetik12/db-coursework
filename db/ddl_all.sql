create type actor_type as enum ('Player', 'Ai');
create type clan_type as enum ('Trade', 'Production', 'Mining');
create type factory_type as enum ('Tools', 'Weapon', 'Smelting');
create type market_action_type as enum ('Created', 'Picked', 'Done', 'Bought');

create type seller_type as enum ('Clan', 'Actor');
create type executor_type as enum ('Clan', 'Actor', 'NoOne');
create type action_type as enum ('Leave', 'Join');

create type contract_type as enum ('Robbery', 'Buy', 'Kill', 'Blank');
create type contract_status as enum ('Open', 'In progress', 'Closed');
create type listing_status as enum ('Open', 'Closed');
create type reward_type as enum ('Purchases', 'Rating', 'Manufacturing');

create table Currency
(
    id    serial primary key,
    name  varchar(80) unique not null                      default 'Ruble',
    price integer            not null check ( price >= 0 ) default 100
);

create table ItemType
(
    id          serial primary key,
    name        varchar(255) unique not null,
    parent_type int references ItemType (id) on update cascade on delete cascade
);

create table Item
(
    id          serial primary key,
    type        int         not null references ItemType (id) on update cascade on delete set null,
    name        text unique not null,
    description text        not null
);

create table FactoryInputItem
(
    id        serial primary key,
    item_id   int not null references Item (id) on update cascade on delete cascade,
    next_item int references FactoryInputItem (id) on update cascade on delete set null
);

create table Factory
(
    id           serial primary key,
    type         factory_type not null,
    productivity float        not null check ( productivity >= 0 and productivity <= 1),
    input_items  int          not null references FactoryInputItem (id) on update cascade on delete set null,
    output_item  int          not null references Item (id) on update cascade on delete set null
);

create table Region
(
    id          serial primary key,
    currency_id integer references Currency (id) on delete set null on update cascade,
    name        varchar(80) unique not null
);

create table Clan
(
    id        serial primary key,
    name      varchar(80) unique not null,
    region_id integer            not null references Region (id) on delete cascade on update cascade,
    type      clan_type          not null,
    rating    integer            not null default 0
);


create table Actor
(
    id      serial primary key,
    clan_id integer references Clan (id) on delete set null on update cascade,
    type    actor_type  not null,
    rating  integer     not null default 0 check ( rating >= 0 ),
    name    varchar(80) not null unique
);


create table ActorClanLog
(
    actor_id    integer references Actor (id) on update cascade on delete cascade,
    clan_id     integer references Clan (id) on update cascade on delete cascade,
    timestamp   timestamp   not null default now(),
    action_type action_type not null default 'Join'
);

create table Inventory
(
    actor_id integer references Actor (id) on delete cascade on update cascade,
    item_id  integer references Item (id) on delete cascade on update cascade,
    amount   integer not null check (amount >= 0),
    primary key (actor_id, item_id)
);

create table FactoryOwner
(
    rel_id     serial primary key,
    clan_id    integer references Clan (id) on update cascade on delete cascade,
    actor_id   integer references Actor (id) on update cascade on delete cascade,
    factory_id integer not null references Factory (id) on update cascade on delete cascade,
    unique (clan_id, actor_id, factory_id)
);

create table Property
(
    id            serial primary key,
    owner_id      integer references Actor (id) on delete set null on update cascade,
    clan_owner_id integer references Clan (id) on delete set null on update cascade
);


create table Listings
(
    listing_id  serial primary key,
    seller      seller_type not null,
    author_id   integer     references Actor (id) on update cascade on delete set null,
    clan_id     integer     references Clan (id) on update cascade on delete set null,
    description text        not null
);

create table ListingHistory
(
    id         serial primary key,
    seller     seller_type        not null,
    action     market_action_type not null,
    author_id  integer references Actor (id) on update cascade on delete cascade,
    clan_id    integer references Clan (id) on update cascade on delete cascade,
    listing_id integer references Listings (listing_id) on update cascade on delete cascade,
    timestamp  timestamp default now()
);

create table ItemListing
(
    listing_id     integer references Listings (listing_id) on update cascade on delete cascade,
    item_id        integer        not null references Item (id) on update cascade on delete cascade,
    price          integer        not null check (price >= 0),
    amount         integer        not null check (price >= 0),
    currency       integer        not null references Currency (id) on update cascade on delete set default,
    status         listing_status not null default 'Open',
    buyer_actor_id integer        references Actor (id) on update cascade on delete set null,
    buyer_clan_id  integer        references Clan (id) on update cascade on delete set null,
    primary key (listing_id)
);

create table Contract
(
    contract_id       serial,
    type              contract_type   not null                     default 'Blank',
    executor_type     executor_type   not null                     default 'NoOne',
    executor_actor_id integer         references Actor (id) on update cascade on delete set null,
    executor_clan_id  integer         references Clan (id) on update cascade on delete set null,
    reward            integer         not null check (reward >= 0) default 0,
    currency          integer         not null references Currency (id) on update cascade on delete set default,
    rating_amount     integer         not null                     default 0,
    status            contract_status not null                     default 'Open',
    listing_id        integer         not null references Listings (listing_id) on update cascade on delete cascade,
    primary key (contract_id, listing_id)
);

create table Reward
(

    id         serial primary key,
    type       reward_type not null,
    owner_id   integer     references Actor (id) on update cascade on delete set null,
    conditions text        not null,
    name       varchar(80) not null unique
);

create or replace function get_item_id_by_name(itemName varchar)
    returns int
    language plpgsql
as
$$
declare
    itemId int;
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
declare
    currencyId int;
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
declare
    itemTypeId int;
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
declare
    clanId int;
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
declare
    rewardId int;
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
declare
    regionId int;
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
declare
    actorId int;
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

create or replace function log_actor_clan_action() returns trigger
    language plpgsql
as
$$
begin
    if old.clan_id is null and new.clan_id is not null then
        insert into actorclanlog(actor_id, clan_id, action_type) values (new.id, new.clan_id, 'Join');
        return new;
    end if;

    if old.clan_id != new.clan_id then
        insert into actorclanlog(actor_id, clan_id, action_type) values (new.id, new.clan_id, 'Join');
        insert into actorclanlog(actor_id, clan_id, action_type) values (new.id, old.clan_id, 'Leave');
        return new;
    end if;

    if old.clan_id is not null and new.clan_id is null then
        insert into actorclanlog(actor_id, clan_id, action_type) values (new.id, old.clan_id, 'Leave');
    end if;
    return new;
end;
$$;

create trigger actor_clan_action
    before update
    on actor
    for each row
execute procedure log_actor_clan_action();

create or replace function log_listing_creation() returns trigger
    language plpgsql
as
$$
begin
    if new.author_id is null then
        insert into listinghistory (seller, action, clan_id, listing_id)
        values (new.seller, 'Created', new.clan_id, new.listing_id);
    end if;
    if new.clan_id is null then
        insert into listinghistory (seller, action, author_id, listing_id)
        values (new.seller, 'Created', new.author_id, new.listing_id);
    end if;
    return new;
end;
$$;

create trigger created_listing
    after insert
    on listings
    for each row
execute procedure log_listing_creation();

create or replace function log_contract_operation() returns trigger
    language plpgsql
as
$$
begin
    if old.executor_actor_id is null and new.executor_actor_id is not null then
        insert into listinghistory (seller, action, author_id, listing_id)
        values ('Actor', 'Picked', new.executor_actor_id, new.listing_id);
        new.status = 'In progress';
    end if;

    if old.executor_clan_id is null and new.executor_clan_id is not null then
        insert into listinghistory (seller, action, clan_id, listing_id)
        values ('Clan', 'Picked', new.executor_clan_id, new.listing_id);
        new.status = 'In progress';
    end if;

    if new.status = 'Closed'::contract_status and new.executor_clan_id is not null then
        insert into listinghistory (seller, action, clan_id, listing_id)
        values ('Clan', 'Done', new.executor_clan_id, new.listing_id);
    end if;

    if new.status = 'Closed'::contract_status and new.executor_actor_id is not null then
        insert into listinghistory (seller, action, author_id, listing_id)
        values ('Actor', 'Done', new.executor_actor_id, new.listing_id);
    end if;

    return new;
end
$$;

create trigger contract_operation
    before update
    on contract
    for each row
execute procedure log_contract_operation();


create or replace function log_bought_item() returns trigger
    language plpgsql
as
$$
begin
    if new.status = 'Closed'::listing_status and new.buyer_actor_id is not null then
        with listingReq as (
            select seller as sel
            from listings
            where listing_id = new.listing_id
        )
        insert
        into listinghistory(seller, action, author_id, listing_id)
        values ((select * from listingReq), 'Bought', new.buyer_actor_id, new.listing_id);
        return new;
    end if;

    if new.status = 'Closed'::listing_status and new.buyer_clan_id is not null then
        with listingReq as (
            select seller
            from listings
            where listing_id = new.listing_id
        )
        insert
        into listinghistory(seller, action, clan_id, listing_id)
        values ((select * from listingReq), 'Bought', new.buyer_clan_id, new.listing_id);
        return new;
    end if;

    if new.status = 'Closed'::listing_status then
        with listingReq as (
            select seller
            from listings
            where listing_id = new.listing_id
        )
        insert
        into listinghistory(seller, action, listing_id)
        values ((select * from listingReq), 'Bought', new.listing_id);
        return new;
    end if;

    return new;
end;
$$;

create trigger item_bought
    before update
    on itemlisting
    for each row
execute procedure log_bought_item();

create index itemNameIndex on Item (name);
create index currencyNameIndex on Currency (name);
create index itemTypeNameIndex on ItemType (name);
create index clanName on Clan (name);
create index rewardName on Reward (name);
create index regionNameIndex on region (name);