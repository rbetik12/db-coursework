import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {Player} from '../models/player.model';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(private cookieService: CookieService) {
    }

    public isAuthenticated(): boolean {
        return !!sessionStorage.getItem('isAuth');
    }

    public authenticate(player: Player): void {
        sessionStorage.setItem('isAuth', String(true));
        this.saveCredentials(player);
    }

    public deAuthenticate(): void {
        sessionStorage.clear();
    }

    public saveCredentials(player: Player): void {
        sessionStorage.setItem('credentials', JSON.stringify(player));
    }

    public getCredentials(): Player | null {
        if (sessionStorage.getItem('credentials') !== null) {
            return JSON.parse(sessionStorage.getItem('credentials') as string);
        }
        return null;
    }
}
