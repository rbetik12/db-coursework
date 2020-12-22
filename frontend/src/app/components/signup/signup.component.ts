import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Player} from '../../models/player.model';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../../injectables/globals.config';
import {MatDialog} from '@angular/material/dialog';
import {UserAlreadyExistsComponent} from '../error-dialogs/user-already-exists/user-already-exists.component';
import {Router} from '@angular/router';

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

    public hide = true;
    public signUpForm = new FormGroup(
        {
            username: new FormControl(
                '',
                [Validators.required]
            ),
            password: new FormControl(
                '',
                [Validators.required]
            ),
            email: new FormControl(
                '',
                [Validators.required]
            )
        }
    );

    public USERNAME = this.signUpForm.controls.username as FormControl;
    public EMAIL = this.signUpForm.controls.email as FormControl;
    public PASSWORD = this.signUpForm.controls.password as FormControl;

    constructor(private http: HttpClient,
                private globals: Globals,
                private dialog: MatDialog,
                private router: Router) {
    }

    ngOnInit(): void {
    }

    public onSubmit(event: Event): void {
        event.preventDefault();
        const player: Player = {
            id: null,
            email: '',
            password: '',
            username: ''
        };
        if (this.signUpForm.controls.username.hasError('required')
            || this.signUpForm.controls.email.hasError('required')
            || this.signUpForm.controls.password.hasError('required')) {
            return;
        }

        const username: string = this.signUpForm.controls.username.value;
        const password: string = this.signUpForm.controls.password.value;
        const email: string = this.signUpForm.controls.email.value;

        player.username = username;
        player.password = password;
        player.email = email;

        this.http.post<Player>(this.globals.address + this.globals.port + '/api/auth/create',
            player,
            {withCredentials: true}).subscribe(
            res => {
            },
            error => {
                this.dialog.open(UserAlreadyExistsComponent);
            });
    }

    toSignInClick(): void {
        this.router.navigateByUrl('/signIn');
    }

}
