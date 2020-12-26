import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {Clan} from '../../models/clan.model';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../../injectables/globals.config';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';
import {CreateClanComponent} from '../dialog/create-clan/create-clan.component';

@Component({
    selector: 'app-clanslist',
    templateUrl: './clanslist.component.html',
    styleUrls: ['./clanslist.component.scss']
})
export class ClanslistComponent implements OnInit {

    public clans: MatTableDataSource<Clan> = new MatTableDataSource<Clan>();
    public clansDisplayedColumns = ['name', 'type', 'rating'];
    public clansAmount = 0;

    @ViewChild('clansListPaginator') paginator: MatPaginator | undefined;

    constructor(private router: Router,
                private http: HttpClient,
                private globals: Globals,
                private dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.http.get(this.globals.address + this.globals.port + '/api/clan/all', {withCredentials: true}).subscribe(
            res => {
               this.clans = new MatTableDataSource<Clan>(res as Clan[]);
               setTimeout(() => this.clans.paginator = this.paginator as MatPaginator | null);
               this.clansAmount = Object.keys(this.clans).length;
            }
        );
    }

    public toProfile(): void {
        this.router.navigateByUrl('/profile');
    }

    public onClanRowClick(row: Clan): void {
        this.router.navigate(['/clan', row.id]);
    }

    public createClan(): void {
        this.dialog.open(CreateClanComponent);
    }

}
