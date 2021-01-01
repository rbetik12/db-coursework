import {Item} from './item.model';
import {Currency} from './currency.model';
import {Actor} from './actor.model';
import {Clan} from './clan.model';
import {Listing} from './listing.model';

export interface ItemListing {
    id: number | null;
    listing: Listing | null;
    item: Item | null;
    price: number | null;
    amount: number | null;
    currency: Currency | null;
    status: string | null;
    buyerActor: Actor | null;
    buyerClan: Clan | null;
}
