import {Listing} from './listing.model';
import {Actor} from './actor.model';
import {Clan} from './clan.model';
import {Currency} from './currency.model';

export interface CurrencyListing {
    id: number;
    listing: Listing | null;
    buyerActor: Actor | null;
    buyerClan: Clan | null;
    currencyForSell: Currency | null;
    currencyForBuy: Currency | null;
    status: string | null;
    sellAmount: number;
    buyAmount: number;
}
