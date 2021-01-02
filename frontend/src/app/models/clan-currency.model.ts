import {Clan} from './clan.model';
import {Currency} from './currency.model';

export interface ClanCurrency {
    clan: Clan;
    currency: Currency;
    amount: number;
}
