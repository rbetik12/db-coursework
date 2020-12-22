import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Player} from '../../models/player.model';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Globals} from '../../injectables/globals.config';
import {Clan} from '../../models/clan.model';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

    public player: Player = {email: '', password: '', id: 0, username: '', actor: null};
    public clan: Clan | null;

    constructor(private authService: AuthService,
                private router: Router,
                private http: HttpClient,
                private globals: Globals) {
        this.clan = null;
    }

    ngOnInit(): void {
        if (this.authService.getCredentials() !== null) {
            this.player = this.authService.getCredentials() as Player;
        }

        const headers = new HttpHeaders();
        headers.append('Content-Type', 'application/json');
        this.http.get(this.globals.address + this.globals.port + '/api/player/clan',
            {withCredentials: true, headers})
            .subscribe(
                res => {
                    this.clan = res as Clan | null;
                },
                error => {
                    console.log(error);
                }
            );
    }

    logout(): void {
        this.authService.deAuthenticate();
        this.router.navigateByUrl('/');
    }

}
