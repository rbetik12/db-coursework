import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
    selector: 'app-clan-page',
    templateUrl: './clan-page.component.html',
    styleUrls: ['./clan-page.component.scss']
})
export class ClanPageComponent implements OnInit {

    public clandId: string | null = '';

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.clandId = this.route.snapshot.paramMap.get('id');
    }

}
