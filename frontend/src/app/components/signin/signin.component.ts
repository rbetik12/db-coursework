import {Component, OnInit} from '@angular/core';
import {Player} from '../../models/player.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';

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

    constructor() {
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
        }
        else {
            player.username = usernameOrEmail;
        }
        player.password = password;
        console.log(player);
    }

}
