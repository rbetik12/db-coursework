import {Listing} from './listing.model';
import {Actor} from './actor.model';
import {Clan} from './clan.model';
import {Currency} from './currency.model';

export interface CurrencyListing {
    id: number;
    listing: Listing;
    buyerActor: Actor;
    buyerClan: Clan;
    currencyForSell: Currency;
    currencyForBuy: Currency;
    status: string;
    sellAmount: number;
    buyAmount: number;
}
