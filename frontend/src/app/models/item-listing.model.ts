import {Item} from './item.model';
import {Currency} from './currency.model';
import {Actor} from './actor.model';
import {Clan} from './clan.model';
import {Listing} from './listing.model';

export interface ItemListing {
    id: number;
    listing: Listing;
    item: Item;
    price: number;
    amount: number;
    currency: Currency;
    status: string;
    buyerActor: Actor;
    buyerClan: Clan;
}
