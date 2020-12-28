import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {CurrencyExchangePrice} from '../../../models/currency-exchange-price.model';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../../../injectables/globals.config';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
    selector: 'app-market-page',
    templateUrl: './market-page.component.html',
    styleUrls: ['./market-page.component.scss']
})
export class MarketPageComponent implements OnInit {

    public currencyPrices: MatTableDataSource<CurrencyExchangePrice> = new MatTableDataSource<CurrencyExchangePrice>();
    public displayedCurrencyColumns = ['currency1', 'currency2', 'price'];
    public currencyAmount = 0;

    @ViewChild('currencyPricePaginator') paginator: MatPaginator | undefined;

    constructor(private router: Router,
                private http: HttpClient,
                private globals: Globals) {
    }

    ngOnInit(): void {
        this.fetchCurrencyPrice();
    }

    private fetchCurrencyPrice(): void {
        this.http.get<CurrencyExchangePrice[]>(this.globals.address + this.globals.port + '/api/currency/price', {withCredentials: true})
            .subscribe(
                res => {
                    this.currencyPrices = new MatTableDataSource<CurrencyExchangePrice>(res);
                    setTimeout(() => {
                        this.currencyPrices.paginator = this.paginator as MatPaginator | null;
                    });
                    this.currencyAmount = Object.keys(this.currencyPrices).length;
                },
                error => {
                    console.error(error);
                }
            );
    }

    public toProfile(): void {
        this.router.navigateByUrl('/profile');
    }

}
