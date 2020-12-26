import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Clan} from '../../../models/clan.model';
import {Globals} from '../../../injectables/globals.config';
import {AuthService} from '../../../services/auth.service';
import {Player} from '../../../models/player.model';

@Component({
    selector: 'app-create-clan',
    templateUrl: './create-clan.component.html',
    styleUrls: ['./create-clan.component.scss']
})
export class CreateClanComponent implements OnInit {

    public clanForm = new FormGroup(
        {
            clanName: new FormControl(
                '',
                Validators.required
            )
        }
    );

    selected = 'Trade';

    public CLAN_NAME = this.clanForm.controls.clanName as FormControl;

    constructor(private http: HttpClient,
                private globals: Globals,
                private auth: AuthService) {
    }

    ngOnInit(): void {
    }

    public submit(): void {
        const clan: Clan = {id: 0, name: this.CLAN_NAME.value, rating: 0, region: {id: 0, name: ''}, type: ''};

        clan.type = this.selected;
        clan.name = this.CLAN_NAME.value;
        const player = this.auth.getCredentials() as Player;
        this.http.post<Clan>(this.globals.address + this.globals.port + '/api/clan/create', clan,
            {
                withCredentials: true,
                params: {
                    id: String(player.id)
                }
            })
            .subscribe(
                res => {
                    console.log(res);
                },
                error => {
                    console.log(error);
                }
            );
    }

}
