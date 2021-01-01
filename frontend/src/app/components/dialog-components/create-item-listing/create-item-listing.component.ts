import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Item} from '../../../models/item.model';
import {Currency} from '../../../models/currency.model';
import {ItemListing} from '../../../models/item-listing.model';
import {AuthService} from '../../../services/auth.service';
import {Actor} from '../../../models/actor.model';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../../../injectables/globals.config';
import {ActorInventory} from '../../../models/actor_inventory.model';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';

@Component({
    selector: 'app-create-item-listing',
    templateUrl: './create-item-listing.component.html',
    styleUrls: ['./create-item-listing.component.scss']
})
export class CreateItemListingComponent implements OnInit {

    public createItemListingForm = new FormGroup(
        {
            price: new FormControl(
                '',
                Validators.required
            ),
            amount: new FormControl(
                '',
                Validators.required
            ),
            description: new FormControl(
                '',
                Validators.required
            )
        }
    );

    public currencyId = 0;
    public itemId = 0;
    public playerItems: Item[] = [];
    public currencyList: Currency[] = [];
    public ITEM_PRICE = this.createItemListingForm.controls.price as FormControl;
    public AMOUNT = this.createItemListingForm.controls.amount as FormControl;
    public DESCRIPTION = this.createItemListingForm.controls.description as FormControl;

    constructor(private auth: AuthService,
                private http: HttpClient,
                private globals: Globals,
                private dialogRef: MatDialogRef<CreateItemListingComponent>) {
    }

    ngOnInit(): void {
        this.fetchPlayerItems();
        this.fetchCurrencyList();
    }

    public fetchCurrencyList(): void {
        this.http.get<Currency[]>(this.globals.address + this.globals.port + '/api/currency/all',
            {withCredentials: true})
            .subscribe(res => {
                    this.currencyList = res;
                },
                error => {
                    console.log(error);
                });
    }

    public fetchPlayerItems(): void {
        this.http.get<ActorInventory[]>(this.globals.address + this.globals.port + '/api/player/inventory', {
            withCredentials: true,
            params: {
                actorId: String(this.auth.getCredentials()?.actor?.id)
            }
        })
            .subscribe(res => {
                    for (const inv of res) {
                        this.playerItems.push(inv.item);
                    }
                },
                error => {
                    console.log(error);
                });
    }

    public submit(e: Event): void {
        e.preventDefault();
        const itemListing: ItemListing = {
            amount: null,
            buyerActor: null,
            buyerClan: null,
            id: null, currency: null, item: null, listing: null, price: null, status: null
        };
        for (const cur of this.currencyList) {
            if (cur.id === this.currencyId) {
                itemListing.currency = cur;
            }
        }

        for (const item of this.playerItems) {
            if (item.id === this.itemId) {
                itemListing.item = item;
            }
        }

        itemListing.listing = {
            id: 0,
            actor: this.auth.getCredentials()?.actor as Actor,
            clan: null, description: this.DESCRIPTION.value, seller: 'Actor'
        };

        itemListing.status = 'Open';
        itemListing.price = this.ITEM_PRICE.value;
        itemListing.amount = this.AMOUNT.value;

        this.http.post(this.globals.address + this.globals.port + '/api/item-listing/create', itemListing,
            {withCredentials: true}).subscribe(res => {
            console.log(res);
        }, error => {
            console.error(error);
        });
        this.dialogRef.close();
    }

}
