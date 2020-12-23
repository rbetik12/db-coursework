import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
    selector: 'app-clanslist',
    templateUrl: './clanslist.component.html',
    styleUrls: ['./clanslist.component.scss']
})
export class ClanslistComponent implements OnInit {

    constructor(private router: Router) {
    }

    ngOnInit(): void {
    }

    public toProfile(): void {
        this.router.navigateByUrl('/profile');
    }

}
