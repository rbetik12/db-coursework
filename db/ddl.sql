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
create type clan_role as enum ('Member', 'Leader');

create table currency
(
    id    serial primary key,
    name  varchar(80) unique not null                      default 'Ruble',
    price integer            not null check ( price >= 0 ) default 100
);

create table item_type
(
    id          serial primary key,
    name        varchar(255) unique not null,
    parent_type int references item_type (id) on update cascade on delete cascade
);

create table item
(
    id          serial primary key,
    type        int         not null references item_type (id) on update cascade on delete set null,
    name        text unique not null,
    description text        not null
);

create table factory_input_item
(
    id        serial primary key,
    item_id   int not null references item (id) on update cascade on delete cascade,
    next_item int references factory_input_item (id) on update cascade on delete set null
);

create table factory
(
    id           serial primary key,
    type         factory_type not null,
    productivity float        not null check ( productivity >= 0 and productivity <= 1),
    input_items  int          not null references factory_input_item (id) on update cascade on delete set null,
    output_item  int          not null references item (id) on update cascade on delete set null
);

create table region
(
    id          serial primary key,
    currency_id integer references currency (id) on delete set null on update cascade,
    name        varchar(80) unique not null
);

create table clan
(
    id        serial primary key,
    name      varchar(80) unique not null,
    region_id integer            not null references region (id) on delete cascade on update cascade,
    type      clan_type          not null,
    rating    integer            not null default 0
);


create table actor
(
    id        serial primary key,
    clan_id   integer references clan (id) on delete set null on update cascade,
    type      actor_type  not null,
    rating    integer     not null default 0 check ( rating >= 0 ),
    clan_role clan_role,
    name      varchar(80) not null unique
);

create table actor_currency
(
    actor_id    int references actor (id) on delete cascade on update cascade,
    currency_id int references currency (id) on delete cascade on update cascade,
    amount      int not null default 0 check ( amount >= 0 ),
    primary key (actor_id, currency_id)
);

create table clan_currency
(
    clan_id     int references clan (id) on delete cascade on update cascade,
    currency_id int references currency (id) on delete cascade on update cascade,
    amount      int not null default 0 check (amount >= 0),
    primary key (clan_id, currency_id)
);


create table actor_clan_log
(
    actor_id    integer references actor (id) on update cascade on delete cascade,
    clan_id     integer references clan (id) on update cascade on delete cascade,
    timestamp   timestamp   not null default now(),
    action_type action_type not null default 'Join'
);

create table actor_inventory
(
    actor_id integer references actor (id) on delete cascade on update cascade,
    item_id  integer references item (id) on delete cascade on update cascade,
    amount   integer not null check (amount >= 0),
    primary key (actor_id, item_id)
);

create table clan_inventory
(
    clan_id integer references clan (id) on delete cascade on update cascade,
    item_id integer references item (id) on delete cascade on update cascade,
    amount  integer not null check (amount >= 0),
    primary key (clan_id, item_id)
);

create table factory_owner
(
    rel_id     serial primary key,
    clan_id    integer references clan (id) on update cascade on delete cascade,
    actor_id   integer references actor (id) on update cascade on delete cascade,
    factory_id integer not null references factory (id) on update cascade on delete cascade,
    unique (clan_id, actor_id, factory_id)
);

create table property
(
    id            serial primary key,
    owner_id      integer references actor (id) on delete set null on update cascade,
    clan_owner_id integer references clan (id) on delete set null on update cascade
);


create table listing
(
    listing_id  serial primary key,
    seller      seller_type not null,
    author_id   integer     references actor (id) on update cascade on delete set null,
    clan_id     integer     references clan (id) on update cascade on delete set null,
    description text        not null
);

create table listing_history
(
    id         serial primary key,
    seller     seller_type        not null,
    action     market_action_type not null,
    author_id  integer references actor (id) on update cascade on delete cascade,
    clan_id    integer references clan (id) on update cascade on delete cascade,
    listing_id integer references listing (listing_id) on update cascade on delete cascade,
    timestamp  timestamp default now()
);

create table item_listing
(
    listing_id     integer references listing (listing_id) on update cascade on delete cascade,
    item_id        integer        not null references item (id) on update cascade on delete cascade,
    price          integer        not null check (price >= 0),
    amount         integer        not null check (price >= 0),
    currency       integer        not null references currency (id) on update cascade on delete set default,
    status         listing_status not null default 'Open',
    buyer_actor_id integer        references actor (id) on update cascade on delete set null,
    buyer_clan_id  integer        references clan (id) on update cascade on delete set null,
    primary key (listing_id)
);

create table contract_listing
(
    contract_id       serial,
    type              contract_type   not null                     default 'Blank',
    executor_type     executor_type   not null                     default 'NoOne',
    executor_actor_id integer         references actor (id) on update cascade on delete set null,
    executor_clan_id  integer         references clan (id) on update cascade on delete set null,
    reward            integer         not null check (reward >= 0) default 0,
    currency          integer         not null references currency (id) on update cascade on delete set default,
    rating_amount     integer         not null                     default 0,
    status            contract_status not null                     default 'Open',
    listing_id        integer         not null references listing (listing_id) on update cascade on delete cascade,
    primary key (contract_id, listing_id)
);

create table property_listing
(
    listing_id     integer references listing (listing_id) on update cascade on delete cascade,
    buyer_actor_id integer        references actor (id) on update cascade on delete set null,
    buyer_clan_id  integer        references clan (id) on update cascade on delete set null,
    property_id    integer references property (id) on update cascade on delete cascade,
    status         listing_status not null default 'Open',
    currency_id    integer references currency (id) on update cascade on delete set default,
    price          integer        not null check ( price >= 0 ),
    primary key (listing_id)
);

create table currency_listing
(
    listing_id           integer references listing (listing_id) on update cascade on delete cascade,
    buyer_actor_id       integer        references actor (id) on update cascade on delete set null,
    buyer_clan_id        integer        references clan (id) on update cascade on delete set null,
    currency_for_sell_id integer references currency (id) on update cascade on delete cascade,
    status               listing_status not null default 'Open',
    currency_for_buy_id  integer references currency (id) on update cascade on delete cascade,
    sell_amount          integer        not null check ( sell_amount >= 0 ),
    buy_amount           integer        not null check ( sell_amount >= 0 ),
    primary key (listing_id)
);

create table factory_listing
(
    listing_id     integer references listing (listing_id) on update cascade on delete cascade,
    buyer_actor_id integer        references actor (id) on update cascade on delete set null,
    buyer_clan_id  integer        references clan (id) on update cascade on delete set null,
    status         listing_status not null default 'Open',
    factory_id     integer references factory (id) on update cascade on delete cascade,
    currency_id    integer references currency (id) on update cascade on delete set default,
    price          integer        not null check ( price >= 0 ),
    primary key (listing_id)
);

create table reward
(

    id         serial primary key,
    type       reward_type not null,
    owner_id   integer     references actor (id) on update cascade on delete set null,
    conditions text        not null,
    name       varchar(80) not null unique
);