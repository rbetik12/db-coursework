drop type if exists actor_type cascade;
drop type if exists clan_type cascade;
drop type if exists factory_type cascade;
drop type if exists contract_type cascade;
drop type if exists contract_status cascade;
drop type if exists reward_type cascade;
drop type if exists market_action_type cascade;
drop type if exists seller_type cascade;
drop type if exists executor_type cascade;
drop type if exists action_type cascade;
drop type if exists listing_status cascade;
drop type if exists clan_role cascade;


drop table if exists currency cascade;
drop table if exists factory cascade;
drop table if exists item cascade;
drop table if exists item_type cascade;
drop table if exists factory_input_item cascade;
drop table if exists region cascade;
drop table if exists actor cascade;
drop table if exists clan cascade;
drop table if exists actor_inventory cascade;
drop table if exists clan_inventory cascade;
drop table if exists factory_owner cascade;
drop table if exists property cascade;
drop table if exists listing cascade;
drop table if exists item_listing cascade;
drop table if exists contract_listing cascade;
drop table if exists reward cascade;
drop table if exists listing_history cascade;
drop table if exists actor_clan_log cascade;
drop table if exists actor_currency cascade;
drop table if exists clan_currency cascade;
drop table if exists property_listing cascade;
drop table if exists currency_listing cascade;
drop table if exists factory_listing cascade;

drop trigger if exists actor_clan_action on actor;
drop trigger if exists created_listing on listings;
drop trigger if exists contract_operation on contract;
drop trigger if exists item_bought on item_listing;
drop trigger if exists actor_check_created_listings on listing_history;
drop trigger if exists check_actor_rating on contract;

drop function if exists get_clan_id_by_name(clanname varchar);
drop function if exists get_region_id_by_name(regionname varchar);
drop function if exists get_currency_id_by_name(currencyname varchar);
drop function if exists get_item_id_by_name(itemname varchar);
drop function if exists get_itemtype_id_by_name(itemtypename varchar);
drop function if exists get_reward_id_by_name(rewardname varchar);

drop index if exists clanRatingIndex;
drop index if exists actorRatingIndex;