import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {Player} from '../models/player.model';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../injectables/globals.config';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(private cookieService: CookieService,
                private http: HttpClient,
                private globals: Globals) {
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

    public async updateCredentials(): Promise<void> {
        const player = await this.http.get<Player>(this.globals.address + this.globals.port + '/api/player/info',
            {
                withCredentials: true, params: {
                    id: JSON.parse(sessionStorage.getItem('credentials') as string).id,
                }
            }).toPromise();
        this.saveCredentials(player);
    }

    public getCredentials(): Player | null {
        if (sessionStorage.getItem('credentials') !== null) {
            return JSON.parse(sessionStorage.getItem('credentials') as string);
        }
        return null;
    }
}
