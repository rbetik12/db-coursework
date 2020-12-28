import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CurrencyExchangePrice} from '../../../models/currency-exchange-price.model';

@Component({
    selector: 'app-market-page',
    templateUrl: './market-page.component.html',
    styleUrls: ['./market-page.component.scss']
})
export class MarketPageComponent implements OnInit {

    public currencyPrice: CurrencyExchangePrice[] = [];
    public displayedCurrencyColumns = ['currency1', 'currency2', 'price'];
    public currencyAmount = 0;

    constructor(private router: Router) {
    }

    ngOnInit(): void {
    }

    public toProfile(): void {
        this.router.navigateByUrl('/profile');
    }

}
