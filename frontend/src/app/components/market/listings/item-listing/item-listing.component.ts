import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../../../../injectables/globals.config';
import {ItemListing} from '../../../../models/item-listing.model';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {Item} from '../../../../models/item.model';

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

    constructor(private router: Router,
                private http: HttpClient,
                private globals: Globals) {
    }

    ngOnInit(): void {
        this.fetchItems();
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
        console.log(listing);
    }

}
