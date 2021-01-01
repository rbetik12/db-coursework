import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../../../../injectables/globals.config';
import {ItemListing} from '../../../../models/item-listing.model';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {Item} from '../../../../models/item.model';
import {AuthService} from '../../../../services/auth.service';
import {MatDialog} from '@angular/material/dialog';
import {CreateItemListingComponent} from '../../../dialog-components/create-item-listing/create-item-listing.component';

@Component({
    selector: 'app-item-listing',
    templateUrl: './item-listing.component.html',
    styleUrls: ['./item-listing.component.scss']
})
export class ItemListingComponent implements OnInit {

    public itemListings: MatTableDataSource<ItemListing> = new MatTableDataSource<ItemListing>();
    public amount = 0;
    public displayedColumns = ['name', 'description', 'currency', 'price', 'status', 'amount', 'action'];
    @ViewChild('itemListingPaginator') paginator: MatPaginator | undefined;

    public isClanLeader = false;

    constructor(private router: Router,
                private http: HttpClient,
                private globals: Globals,
                private auth: AuthService,
                private dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.fetchItems();
        if (this.auth.getCredentials()?.actor?.clanRole === 'Leader') {
            this.isClanLeader = true;
        }
    }

    private fetchItems(): void {
        this.http.get<ItemListing[]>(this.globals.address + this.globals.port + '/api/item-listing/all',
            {withCredentials: true})
            .subscribe(
                res => {
                    this.itemListings.data = res;
                    setTimeout(() => {
                        this.itemListings.paginator = this.paginator as MatPaginator | null;
                    });
                },
                error => {
                    console.log(error);
                }
            );
    }

    public toProfile(): void {
        this.router.navigateByUrl('/profile');
    }

    public toMarket(): void {
        this.router.navigateByUrl('/market');
    }

    public buy(listing: ItemListing): void {
        this.http.post(this.globals.address + this.globals.port + '/api/item-listing/buy', listing,
            {
                withCredentials: true,
                params: {
                    id: String(this.auth.getCredentials()?.actor?.id)
                }
            }).subscribe(
            res => {
                this.fetchItems();
            },
            error => {
                console.log(error);
            }
        );
    }

    public buyAsClan(listing: ItemListing): void {
        this.http.post(this.globals.address + this.globals.port + '/api/item-listing/buy-as-clan', listing,
            {
                withCredentials: true,
                params: {
                    clanId: String(this.auth.getCredentials()?.actor?.clan.id),
                    actorId: String(this.auth.getCredentials()?.actor?.id)
                }
            }).subscribe(
            res => {
                console.log(res);
            },
            error => {
                console.log(error);
            }
        );
    }

    public newListing(): void {
        this.dialog.open(CreateItemListingComponent);
    }

}
