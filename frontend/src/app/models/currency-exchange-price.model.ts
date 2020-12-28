import {Currency} from './currency.model';

export interface CurrencyExchangePrice {
    currency1: Currency;
    currency2: Currency;
    price: number;
}
