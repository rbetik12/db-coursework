import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Player} from '../../models/player.model';
import {Router} from '@angular/router';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

    public player: Player = {email: '', password: '', id: 0, username: ''};

    constructor(private authService: AuthService,
                private router: Router) {
    }

    ngOnInit(): void {
        if (this.authService.getCredentials() !== null) {
            this.player = this.authService.getCredentials() as Player;
        }
    }

    logout(): void {
        this.authService.deAuthenticate();
        this.router.navigateByUrl('/');
    }

}