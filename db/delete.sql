drop type if exists actor_type cascade;
drop type if exists clan_type cascade;
drop type if exists item_subtype cascade;
drop type if exists factory_type cascade;
drop type if exists contract_type cascade;
drop type if exists contract_status cascade;
drop type if exists reward_type cascade;
drop type if exists market_action_type cascade;
drop type if exists seller_type cascade;
drop type if exists executor_type cascade;
drop type if exists action_type cascade;
drop type if exists listing_status cascade;


drop table if exists Currency cascade;
drop table if exists Factory cascade;
drop table if exists Item cascade;
drop table if exists item_type cascade;
drop table if exists FactoryInputItem cascade;
drop table if exists Region cascade;
drop table if exists Actor cascade;
drop table if exists Clan cascade;
drop table if exists Inventory cascade;
drop table if exists FactoryOwner cascade;
drop table if exists Property cascade;
drop table if exists Listings cascade;
drop table if exists ItemListing cascade;
drop table if exists Contract cascade;
drop table if exists Reward cascade;
drop table if exists PlayerRating cascade;
drop table if exists ClanRating cascade;
drop table if exists ListingHistory cascade;
drop table if exists ActorClanLog cascade;
drop table if exists actorcurrency cascade;
drop table if exists clancurrency cascade;
drop table if exists propertylisting cascade;
drop table if exists currencylisting cascade;
drop table if exists factorylisting cascade;

drop trigger if exists actor_clan_action on actor;
drop trigger if exists created_listing on listings;
drop trigger if exists contract_operation on contract;
drop trigger if exists item_bought on itemlisting;
drop trigger if exists actor_check_created_listings on listinghistory;
drop trigger if exists check_actor_rating on contract;

drop function if exists getclanidbyname(clanname varchar);
drop function if exists getregionidbyname(regionname varchar);
drop function if exists getcurrencyidbyname(currencyname varchar);
drop function if exists getitemidbyname(itemname varchar);
drop function if exists getitemtypeidbyname(itemtypename varchar);
drop function if exists getrewardidbyname(rewardname varchar);

drop index if exists clanRatingIndex;
drop index if exists actorRatingIndex;