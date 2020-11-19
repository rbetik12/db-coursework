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
    input_items  int          not null references FactoryInputItem (id) on update cascade on delete set null
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
    rating    integer            not null default 0 check ( rating >= 0 )
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


