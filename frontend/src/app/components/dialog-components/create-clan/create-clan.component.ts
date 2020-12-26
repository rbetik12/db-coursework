import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Clan} from '../../../models/clan.model';
import {Globals} from '../../../injectables/globals.config';
import {AuthService} from '../../../services/auth.service';
import {Player} from '../../../models/player.model';
import {MatDialogRef} from '@angular/material/dialog';
import {Region} from '../../../models/region.model';

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

    public selected = 'Trade';
    public regionId = 0;
    public regions: Region[] = [];
    public CLAN_NAME = this.clanForm.controls.clanName as FormControl;

    constructor(private http: HttpClient,
                private globals: Globals,
                private auth: AuthService,
                private dialogRef: MatDialogRef<CreateClanComponent>) {
    }

    async ngOnInit(): Promise<void> {
        this.regions = await this.loadRegionsList();
    }

    private async loadRegionsList(): Promise<Region[]> {
        return await this.http.get<Region[]>(this.globals.address + this.globals.port + '/api/region/all',
            {
                withCredentials: true,
                params: {amount: '10'}
            }
        ).toPromise();
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
                    this.dialogRef.close();
                },
                error => {
                    console.log(error);
                }
            );
    }

}
