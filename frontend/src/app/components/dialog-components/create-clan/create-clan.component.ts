import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Clan} from '../../../models/clan.model';
import {Globals} from '../../../injectables/globals.config';
import {AuthService} from '../../../services/auth.service';
import {Player} from '../../../models/player.model';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Region} from '../../../models/region.model';
import {AlreadyInClanComponent} from '../../error-dialogs/already-in-clan/already-in-clan.component';

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
                private dialogRef: MatDialogRef<CreateClanComponent>,
                private dialog: MatDialog) {
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
        const selectedRegion: Region = {id: 0, name: ''};

        for (const reg of this.regions) {
            if (reg.id === this.regionId) {
                selectedRegion.id = reg.id;
                selectedRegion.name = reg.name;
            }
        }
        clan.region = selectedRegion;
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
                    this.auth.updateCredentials();
                    this.dialogRef.close();
                },
                error => {
                    this.dialogRef.close();
                    this.dialog.open(AlreadyInClanComponent);
                }
            );
    }

}
