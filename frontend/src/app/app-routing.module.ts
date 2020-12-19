import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SigninComponent} from './components/signin/signin.component';
import {WelcomeComponent} from './components/welcome/welcome.component';

const routes: Routes = [
    {path: '', component: WelcomeComponent},
    {path: 'signIn', component: SigninComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
