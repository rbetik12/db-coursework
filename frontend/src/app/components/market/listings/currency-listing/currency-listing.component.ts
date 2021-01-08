import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../../../../services/auth.service';
import {Globals} from '../../../../injectables/globals.config';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {CurrencyListing} from '../../../../models/currency-listing.model';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {CreateCurrencyListingComponent} from '../../../dialog-components/create-currency-listing/create-currency-listing.component';

@Component({
    selector: 'app-currency-listing',
    templateUrl: './currency-listing.component.html',
    styleUrls: ['./currency-listing.component.scss']
})
export class CurrencyListingComponent implements OnInit {

    public isClanLeader = false;
    public displayedColumns = ['description', 'currency-for-sell', 'currency-for-buy', 'sellAmount', 'buyAmount', 'status', 'action'];
    public amount = 0;
    @ViewChild('currencyListingPaginator') paginator: MatPaginator | undefined;
    public currencyListings: MatTableDataSource<CurrencyListing> = new MatTableDataSource<CurrencyListing>();

    constructor(private http: HttpClient,
                private auth: AuthService,
                private globals: Globals,
                private router: Router,
                private dialog: MatDialog) {
    }

    ngOnInit(): void {
        if (this.auth.getCredentials()?.actor?.clan) {
            this.isClanLeader = true;
        }
        this.fetchListings();
    }

    private fetchListings(): void {
        this.http.get<CurrencyListing[]>(this.globals.address + this.globals.port + '/api/currency-listing/all',
            {withCredentials: true})
            .subscribe(res => {
                this.currencyListings.data = res;
                setTimeout(() => {
                    this.currencyListings.paginator = this.paginator as MatPaginator;
                });
            });
    }

    public toProfile(): void {
        this.router.navigateByUrl('/profile');
    }

    public newListing(): void {
        this.dialog.open(CreateCurrencyListingComponent);
    }

    public buy(listing: CurrencyListing): void {
        this.http.post(this.globals.address + this.globals.port + '/api/currency-listing/buy', listing, {
            withCredentials: true, params: {
                actorId: String(this.auth.getCredentials()?.actor?.id)
            }
        }).subscribe(res => {
                console.log(res);
            },
            error => {
                console.error(error);
            });
    }

    public buyAsClan(listing: CurrencyListing): void {
        this.http.post(this.globals.address + this.globals.port + '/api/currency-listing/buy-as-clan', listing, {
            withCredentials: true,
            params: {
                clanId: String(this.auth.getCredentials()?.actor?.clan.id)
            }
        }).subscribe(res => {
                console.log(res);
            },
            error => {
                console.error(error);
            });
    }
}
