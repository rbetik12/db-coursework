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
    before update on contract
    for each row
    execute procedure log_contract_operation();

create or replace function log_bought_item() returns trigger
language plpgsql
as
    $$
    begin
        if new.status = 'Closed'::listing_status and new.buyer_actor_id is not null then
            with listingReq as (
                select seller as sel from listings where listing_id = new.listing_id
            )
            insert into listinghistory(seller, action, author_id, listing_id) values ((select * from listingReq), 'Bought', new.buyer_actor_id, new.listing_id);
            return new;
        end if;

        if new.status = 'Closed'::listing_status and new.buyer_clan_id is not null then
            with listingReq as (
                select seller from listings where listing_id = new.listing_id
            )
            insert into listinghistory(seller, action, clan_id, listing_id) values ((select * from listingReq), 'Bought', new.buyer_clan_id, new.listing_id);
            return new;
        end if;

        if new.status = 'Closed'::listing_status then
            with listingReq as (
                select seller from listings where listing_id = new.listing_id
            )
            insert into listinghistory(seller, action, listing_id) values ((select * from listingReq), 'Bought', new.listing_id);
            return new;
        end if;

        return new;
    end;
    $$;

create trigger item_bought
    before update on itemlisting
    for each row
    execute procedure  log_bought_item();
