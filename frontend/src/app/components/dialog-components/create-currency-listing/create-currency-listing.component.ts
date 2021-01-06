import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Currency} from '../../../models/currency.model';
import {AuthService} from '../../../services/auth.service';
import {Globals} from '../../../injectables/globals.config';
import {HttpClient} from '@angular/common/http';
import {ActorCurrency} from '../../../models/actor_currency.model';
import {CurrencyListing} from '../../../models/currency-listing.model';
import {Listing} from '../../../models/listing.model';
import {Actor} from '../../../models/actor.model';

@Component({
    selector: 'app-create-currency-listing',
    templateUrl: './create-currency-listing.component.html',
    styleUrls: ['./create-currency-listing.component.scss']
})
export class CreateCurrencyListingComponent implements OnInit {

    public createCurrencyListingForm = new FormGroup(
        {
            description: new FormControl('', Validators.required),
            sellAmount: new FormControl('', Validators.required),
            buyAmount: new FormControl('', Validators.required)
        }
    );
    public currencyForSellId = 0;
    public currencyForBuyId = 0;
    public playerCurrency: ActorCurrency[] = [];
    public currencyList: Currency[] = [];
    public SELL_AMOUNT = this.createCurrencyListingForm.controls.sellAmount as FormControl;
    public BUY_AMOUNT = this.createCurrencyListingForm.controls.buyAmount as FormControl;
    public DESCRIPTION = this.createCurrencyListingForm.controls.description as FormControl;

    constructor(private auth: AuthService,
                private globals: Globals,
                private http: HttpClient) {
    }

    ngOnInit(): void {
        this.fetchPlayerCurrency();
        this.fetchCurrency();
    }

    public fetchPlayerCurrency(): void {
        this.http.get<ActorCurrency[]>(this.globals.address + this.globals.port + '/api/player/currency',
            {
                withCredentials: true, params: {
                    actorId: String(this.auth.getCredentials()?.actor?.id)
                }
            }).subscribe(
            res => {
                this.playerCurrency = res;
            }
        );
    }

    public fetchCurrency(): void {
        this.http.get<Currency[]>(this.globals.address + this.globals.port + '/api/currency/all',
            {
                withCredentials: true
            }).subscribe(
            res => {
                this.currencyList = res;
            }
        );
    }

    public submit(event: Event): void {
        event.preventDefault();
        const currencyListing: CurrencyListing = {
            currencyForSell: null, buyAmount: 0, buyerActor: null, buyerClan: null,
            currencyForBuy: null, id: 0, listing: null, sellAmount: 0, status: null
        };

        for (const cur of this.currencyList) {
            if (cur.id === this.currencyForBuyId) {
                currencyListing.currencyForBuy = cur;
            }
        }

        for (const cur of this.playerCurrency) {
            if (cur.currency.id === this.currencyForSellId) {
                currencyListing.currencyForSell = cur.currency;
            }
        }

        currencyListing.sellAmount = this.SELL_AMOUNT.value;
        currencyListing.buyAmount = this.BUY_AMOUNT.value;

        const listing: Listing = {
            id: 0,
            seller: 'Actor',
            description: this.DESCRIPTION.value,
            actor: this.auth.getCredentials()?.actor as Actor,
            clan: null
        };

        currencyListing.listing = listing;
        currencyListing.status = 'Open';

        this.http.post(this.globals.address + this.globals.port + '/api/currency-listing/create', currencyListing,
            {withCredentials: true})
            .subscribe(res => {
                    console.log(res);
                },
                error => {
                    console.error(error);
                });
    }

}
