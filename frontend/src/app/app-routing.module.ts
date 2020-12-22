import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SigninComponent} from './components/signin/signin.component';
import {WelcomeComponent} from './components/welcome/welcome.component';
import {SignupComponent} from './components/signup/signup.component';
import {ProfileComponent} from './components/profile/profile.component';
import {AuthGuard} from './guards/auth.guard';

const routes: Routes = [
    {path: '', component: WelcomeComponent},
    {path: 'signIn', component: SigninComponent},
    {path: 'signUp', component: SignupComponent},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
