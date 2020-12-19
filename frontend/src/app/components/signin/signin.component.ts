import {Component, OnInit} from '@angular/core';
import {Player} from '../../models/player.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../../injectables/globals.config';

@Component({
    selector: 'app-signin',
    templateUrl: './signin.component.html',
    styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

    public hide = true;
    public signInForm = new FormGroup(
        {
            usernameOrEmail: new FormControl(
                '',
                [Validators.required]
            ),
            password: new FormControl(
                '',
                [Validators.required]
            )
        }
    );

    public USER_OR_EMAIL = this.signInForm.controls.usernameOrEmail as FormControl;
    public PASSWORD = this.signInForm.controls.password as FormControl;

    constructor(private http: HttpClient,
                private globals: Globals) {
    }

    ngOnInit(): void {
    }

    onSubmit(event: Event): void {
        event.preventDefault();
        const player: Player = {
            id: null,
            email: '',
            password: '',
            username: ''
        };
        if (this.signInForm.controls.usernameOrEmail.hasError('required')
            || this.signInForm.controls.password.hasError('required')) {
            return;
        }
        const usernameOrEmail: string = this.signInForm.controls.usernameOrEmail.value;
        const password: string = this.signInForm.controls.password.value;
        if (usernameOrEmail.includes('@')) {
            player.email = usernameOrEmail;
        } else {
            player.username = usernameOrEmail;
        }
        player.password = password;

        console.log(JSON.stringify(player));
        this.http.post<Player>(this.globals.address + this.globals.port + '/api/auth/signIn',
            JSON.parse(JSON.stringify(player)), {withCredentials: true}).subscribe(
            res => {
                console.log(res);
            },
            error => {
                console.log(error);
            });
    }
}
