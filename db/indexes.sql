create index itemNameIndex on Item using hash(name);
create index currencyNameIndex on Currency using hash(name);
create index itemTypeNameIndex on ItemType using hash(name);
create index clanName on Clan using hash(name);
create index rewardName on Reward using hash(name);
create index regionNameIndex on region using hash(name);

create index ratingIndex on actor (rating);
create index clanRating on clan (rating);
