import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Globals} from '../../injectables/globals.config';
import {Clan} from '../../models/clan.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MatDialog} from '@angular/material/dialog';
import {AlreadyInClanComponent} from '../error-dialogs/already-in-clan/already-in-clan.component';
import {AuthService} from '../../services/auth.service';
import {Player} from '../../models/player.model';
import {ClanCurrency} from '../../models/clan-currency.model';
import {ClanInventory} from '../../models/clan-inventory.model';

@Component({
    selector: 'app-clan-page',
    templateUrl: './clan-page.component.html',
    styleUrls: ['./clan-page.component.scss']
})
export class ClanPageComponent implements OnInit {

    public clandId: string | null = '';
    public clan: Clan = {id: 0, name: '', rating: 0, type: '', region: {name: '', id: 0}};
    public isPlayerClan = false;
    public displayedInventoryColumns = ['item', 'amount'];
    public displayedCurrencyColumns = ['currency', 'amount'];
    public currency: ClanCurrency[] = [];
    public items: ClanInventory[] = [];
    public itemsAmount = 0;
    public currencyAmount = 0;


    constructor(private route: ActivatedRoute,
                private router: Router,
                private globals: Globals,
                private http: HttpClient,
                private dialog: MatDialog,
                private auth: AuthService) {
    }

    ngOnInit(): void {
        this.clandId = this.route.snapshot.paramMap.get('id');

        this.fetchClanInfo();
        this.checkForPlayerClan();
        if (this.isPlayerClan) {
            this.fetchCurrency();
            this.fetchInventory();
        }
    }

    public fetchCurrency(): void {
        this.http.get<ClanCurrency[]>(this.globals.address + this.globals.port + '/api/clan/currency',
            {
                withCredentials: true, params: {
                    clanId: String(this.clandId)
                }
            })
            .subscribe(
                res => {
                    this.currency = res;
                }
            );
    }

    public fetchInventory(): void {
        this.http.get<ClanInventory[]>(this.globals.address + this.globals.port + '/api/clan/inventory',
            {
                withCredentials: true, params: {
                    clanId: String(this.clandId)
                }
            })
            .subscribe(
                res => {
                    this.items = res;
                }
            );
    }

    public checkForPlayerClan(): void {
        const creds: Player = this.auth.getCredentials() as Player;
        if (creds.actor === null || creds.actor.clan === null) {
            return;
        }

        this.isPlayerClan = creds.actor.clan.id === Number(this.clandId);
    }

    public fetchClanInfo(): void {
        this.http.get(this.globals.address + this.globals.port + '/api/clan/info',
            {
                withCredentials: true,
                params: {id: this.clandId as string}
            }).subscribe(
            res => {
                this.clan = res as Clan;
                this.checkForPlayerClan();
            }
        );
    }

    public toProfile(): void {
        this.router.navigateByUrl('/profile');
    }

    public toClansList(): void {
        this.router.navigateByUrl('/clans');
    }

    public joinClan(): void {
        const headers = new HttpHeaders();
        headers.append('Content-Type', 'application/json');
        this.http.post<Clan>(this.globals.address + this.globals.port + '/api/clan/join', this.clan,
            {withCredentials: true, headers})
            .subscribe(
                async res => {
                    await this.auth.updateCredentials();
                    this.checkForPlayerClan();
                    this.fetchCurrency();
                    this.fetchInventory();
                },
                error => {
                    this.dialog.open(AlreadyInClanComponent);
                }
            );
    }

    public leaveClan(): void {
        const headers = new HttpHeaders();
        headers.append('Content-Type', 'application/json');
        this.http.post<Clan>(this.globals.address + this.globals.port + '/api/clan/leave', this.clan,
            {withCredentials: true, headers})
            .subscribe(
                async res => {
                    await this.auth.updateCredentials();
                    this.isPlayerClan = false;
                },
                error => {
                    console.log(error);
                }
            );
    }

    public toMarket(): void {
        this.router.navigateByUrl('/market');
    }

}
