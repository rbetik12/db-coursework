insert into currency default values;
insert into currency(name, price) values ('Gold', 100),
                                         ('Silver', 60),
                                         ('Dollars', 76),
                                         ('Platinum', 110),
                                         ('Crona', 10),
                                         ('Lira', 10),
                                         ('Vibranium', 79);

insert into region(currency_id, name)
values (get_currency_id_by_name('Gold'), 'Landia'),
       (get_currency_id_by_name('Dollars'), 'West'),
       (get_currency_id_by_name('Silver'), 'Land'),
       (get_currency_id_by_name('Platinum'), 'PlatLandia'),
       (get_currency_id_by_name('Crona'), 'Normand'),
       (get_currency_id_by_name('Lira'), 'EastLand'),
       (get_currency_id_by_name('Vibranium'), 'Wakanda');

insert into Clan (name, region_id, type)
values ('Rome Clan', get_region_id_by_name('Landia'), 'Production'),
       ('Normand Clan', get_region_id_by_name('Land'), 'Production'),
       ('Cool clan', get_region_id_by_name('West'), 'Trade'),
       ('Romania Clan', get_region_id_by_name('Landia'), 'Production'),
       ('Normand Legends Clan', get_region_id_by_name('Land'), 'Production'),
       ('Redneck-west clan', get_region_id_by_name('West'), 'Trade'),
       ('Metals Clan', get_region_id_by_name('PlatLandia'), 'Production'),
       ('Wikings Legends Clan', get_region_id_by_name('Normand'), 'Production'),
       ('Aladin clan', get_region_id_by_name('EastLand'), 'Trade'),
       ('Wakanada clan', get_region_id_by_name('Wakanda'), 'Trade');

insert into actor(type, name)
values ('Player', 'rbetik12'),
       ('Ai', 'ai1'),
       ('Player', 'rost12'),
       ('Player', 'vitalya1'),
       ('Player', 'godplayer'),
       ('Player', 'weeking king'),
       ('Player', 'terminator'),
       ('Player', 'lunatik14'),
       ('Player', 'yoda33'),
       ('Player', 'winner454'),
       ('Player', 'sasha33'),
       ('Player', 'vadim123123'),
       ('Player', 'gggg4444'),
       ('Player', 'vitya'),
       ('Player', 'coolplayer'),
       ('Player', 'rbetik');

insert into itemtype (name, parent_type) values ('Tool', null),
                                                ('Weapon', null),
                                                ('Food', null),
                                                ('Material', null);


insert into item (type, name, description)
values (get_itemtype_id_by_name('Tool'), 'Pickaxe', 'Tool for mining'),
        (get_itemtype_id_by_name('Weapon'), 'Sword', 'Deadly weapon'),
        (get_itemtype_id_by_name('Food'), 'Apple', 'Sweet apple'),
        (get_itemtype_id_by_name('Tool'), 'Stick', 'Basic tool for mining'),
        (get_itemtype_id_by_name('Weapon'), 'Gun', 'Immediate death'),
        (get_itemtype_id_by_name('Food'), 'Meat', 'Fresh meat'),
        (get_itemtype_id_by_name('Tool'), 'Table', 'Basic tool for making tools'),
        (get_itemtype_id_by_name('Weapon'), 'Bow with arrows', 'Effective and cheap weapon'),
        (get_itemtype_id_by_name('Food'), 'Fish', 'Tasty energy'),
        (get_itemtype_id_by_name('Tool'), 'Drill', 'Tool for destructing something'),
        (get_itemtype_id_by_name('Weapon'), 'Knife', 'Awesome and effective'),
        (get_itemtype_id_by_name('Food'), 'Cherry', 'Tasty desert'),
       (get_itemtype_id_by_name('Material'), 'Wood', 'Wood'),
        (get_itemtype_id_by_name('Material'), 'Iron', 'Iron'),
        (get_itemtype_id_by_name('Material'), 'Copper', 'Copper'),
        (get_itemtype_id_by_name('Material'), 'Gold', 'Gold');

select create_new_contract_for_clan(
    get_clan_id_by_name('Rome Clan'), 'Kill',
    100,
    get_currency_id_by_name('Gold'), 150,
    'To kill a bad guy');

select create_new_contract_for_actor(
    get_actor_id_by_name('rbetik12'), 'Kill',
    150,
    get_currency_id_by_name('Gold'), -200,
    'To kill a good guy');
select create_new_contract_for_actor(
    get_actor_id_by_name('godplayer'), 'Kill',
    150,
    get_currency_id_by_name('Gold'), -300,
    'To kill a good guy');

select create_new_item_listing_for_actor(
    get_actor_id_by_name('rbetik12'),
    get_item_id_by_name('Apple'), 228, 10,
    get_currency_id_by_name('Gold'), 'Selling apples');
select create_new_item_listing_for_actor(
    get_actor_id_by_name('godplayer'),
    get_item_id_by_name('Meat'), 228, 10,
    get_currency_id_by_name('Gold'), 'Selling meat');

select create_new_item_listing_for_actor(
    get_actor_id_by_name('rbetik12'),
    get_item_id_by_name('Knife'), 1, 1,
    get_currency_id_by_name('Gold'), 'Selling 1 knife');


select create_new_item_listing_for_clan(
    get_clan_id_by_name('Rome Clan'),
    get_item_id_by_name('Apple'), 228, 10,
    get_currency_id_by_name('Gold'), 'Selling apples');
select create_new_item_listing_for_clan(
    get_clan_id_by_name('Wakanada Clan'),
    get_item_id_by_name('Stick'), 22, 2,
    get_currency_id_by_name('Vibranium'), 'Selling sticks');

insert into factoryinputitem(item_id, next_item)
values (get_item_id_by_name('Apple'), null),
(get_item_id_by_name('Stick'), get_item_id_by_name('table')),
(get_item_id_by_name('Table'), get_item_id_by_name('drill')),
(get_item_id_by_name('Bow with arrows'), get_item_id_by_name('gun'));

insert into factoryinputitem(item_id, next_item)
values (get_item_id_by_name('Sword'), 2);

insert into factoryinputitem(item_id) values (get_item_id_by_name('Iron')),
                                             (get_item_id_by_name('Copper')),
                                             (get_item_id_by_name('Gold'));

insert into item (type, name, description) values (get_itemtype_id_by_name('Material'), 'Iron bar', 'Iron bar for crafting');
insert into item (type, name, description) values (get_itemtype_id_by_name('Material'), 'Golden bar', 'Golden bar for crafting');

insert into factory (type, productivity, input_items, output_item) values ('Tools', 0.5, 6, get_item_id_by_name('Knife')),
                                                                          ('Smelting', 0.5, 6, get_item_id_by_name('Iron bar')),
                                                                          ('Smelting', 0.5, 8, get_item_id_by_name('Golden bar'));

insert into factoryowner(clan_id, factory_id) values (get_clan_id_by_name('Rome Сlan'), 1),
                                                     (get_clan_id_by_name('Normand Clan'), 2);

insert into factoryowner(actor_id, factory_id) values (get_actor_id_by_name('rbetik12'), 1),
                                                      (get_actor_id_by_name('rbetik'), 2);

insert into inventory (actor_id, item_id, amount) values (get_actor_id_by_name('rbetik12'), get_item_id_by_name('Iron bar'), 100),
                                                         (get_actor_id_by_name('rbetik'), get_item_id_by_name('Golden bar'), 100);

insert into property (owner_id) values (get_actor_id_by_name('rbetik12')),
                                       (get_actor_id_by_name('rbetik'));

insert into property (clan_owner_id) values (get_clan_id_by_name('Rome Clan')),
                                            (get_clan_id_by_name('Normand Clan'));

insert into reward (type, owner_id, conditions, name)
values ('Purchases', get_actor_id_by_name('rbetik12'), 'Kek', 'Cool stuff'),
       ('Rating', get_actor_id_by_name('rbetik'), 'Own 2 houses', 'Achievemnt'),
        ('Manufacturing', get_actor_id_by_name('godplayer'), 'Create two iron bars', 'Achievemnt2');

update actor set clan_id = get_clan_id_by_name('Rome Clan')
                 where id = get_actor_id_by_name('rbetik12');

update actor set clan_id = get_clan_id_by_name('Normand Clan') where id > 0;

update contract set status = 'Open', executor_actor_id = get_actor_id_by_name('rbetik12') where contract_id = 1;
update contract set status = 'In progress' where contract_id = 1;
update contract set status = 'Closed' where contract_id = 1;