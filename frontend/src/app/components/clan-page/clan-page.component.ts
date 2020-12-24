import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Globals} from '../../injectables/globals.config';
import {Clan} from '../../models/clan.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MatDialog} from '@angular/material/dialog';
import {AlreadyInClanComponent} from '../error-dialogs/already-in-clan/already-in-clan.component';

@Component({
    selector: 'app-clan-page',
    templateUrl: './clan-page.component.html',
    styleUrls: ['./clan-page.component.scss']
})
export class ClanPageComponent implements OnInit {

    public clandId: string | null = '';
    public clan: Clan = {id: 0, name: '', rating: 0, type: '', region: {name: '', id: 0}};

    constructor(private route: ActivatedRoute,
                private router: Router,
                private globals: Globals,
                private http: HttpClient,
                private dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.clandId = this.route.snapshot.paramMap.get('id');

        this.fetchClanInfo();
    }

    public fetchClanInfo(): void {
        this.http.get(this.globals.address + this.globals.port + '/api/clan/info',
            {
                withCredentials: true,
                params: {id: this.clandId as string}
            }).subscribe(
                res => {
                    this.clan = res as Clan;
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
                res => {
                },
                error => {
                    this.dialog.open(AlreadyInClanComponent);
                }
            );
    }

}
