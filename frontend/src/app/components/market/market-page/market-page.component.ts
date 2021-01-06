import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {CurrencyExchangePrice} from '../../../models/currency-exchange-price.model';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../../../injectables/globals.config';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {ItemListing} from '../../../models/item-listing.model';
import {Currency} from '../../../models/currency.model';

@Component({
    selector: 'app-market-page',
    templateUrl: './market-page.component.html',
    styleUrls: ['./market-page.component.scss']
})
export class MarketPageComponent implements OnInit {

    public currencyPrices: MatTableDataSource<CurrencyExchangePrice> = new MatTableDataSource<CurrencyExchangePrice>();
    public displayedCurrencyColumns = ['currency1', 'currency2', 'price'];
    public currencyAmount = 0;

    public itemName = '';
    public currencyList: Currency[] = [];
    public currencyId = 0;
    public itemPriceResult = '';

    @ViewChild('currencyPricePaginator') paginator: MatPaginator | undefined;

    constructor(private router: Router,
                private http: HttpClient,
                private globals: Globals) {
    }

    ngOnInit(): void {
        this.fetchCurrency();
        this.fetchCurrencyPrice();
    }

    private fetchCurrency(): void {
        this.http.get<Currency[]>(this.globals.address + this.globals.port + '/api/currency/all', {withCredentials: true})
            .subscribe(res => {
                    this.currencyList = res;
                },
                error => {
                    console.error(error);
                });
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

    public toItems(): void {
        this.router.navigateByUrl('/item-listings');
    }

    public toProperty(): void {

    }

    public toCurrency(): void {
        this.router.navigateByUrl('/currency-listings');
    }

    public toContracts(): void {

    }

    public fetchItemPrice(): void {
        this.http.get(this.globals.address + this.globals.port + '/api/item-listing/price',
            {
                withCredentials: true, params: {
                    itemName: String(this.itemName),
                    currencyId: String(this.currencyId)
                }
            })
            .subscribe((res: any) => {
                    this.itemPriceResult = res.text as string;
                },
                error => {
                    console.error(error);
                });
    }

}
