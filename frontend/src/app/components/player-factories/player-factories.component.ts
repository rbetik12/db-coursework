import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../../services/auth.service';
import {Globals} from '../../injectables/globals.config';
import {Factory} from '../../models/factory.model';
import {FactoryInputItem} from '../../models/factory-input-item.model';

@Component({
    selector: 'app-player-factories',
    templateUrl: './player-factories.component.html',
    styleUrls: ['./player-factories.component.scss']
})
export class PlayerFactoriesComponent implements OnInit {

    public factories: Factory[] = [];

    constructor(private router: Router,
                private http: HttpClient,
                private auth: AuthService,
                private globals: Globals) {
    }

    ngOnInit(): void {
        this.fetchFactories();
    }

    public toProfile(): void {
        this.router.navigateByUrl('/profile');
    }

    public fetchFactories(): void {
        this.http.get<Factory[]>(this.globals.address + this.globals.port + '/api/factory/all', {withCredentials: true})
            .subscribe(res => {
                this.factories = res;
            });
    }

    private inputItemsList(factory: Factory): FactoryInputItem[] {
        const inputItems: FactoryInputItem[] = [];
        this.recItem(inputItems, factory.inputItems);
        return inputItems;
    }

    private recItem(inputItems: FactoryInputItem[], inputItem: FactoryInputItem): void {
        inputItems.push(inputItem);
        if (inputItem.next === null) {
            return;
        }
        this.recItem(inputItems, inputItem.next);
    }
}
