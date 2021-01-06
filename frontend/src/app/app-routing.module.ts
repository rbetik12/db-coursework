import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SigninComponent} from './components/signin/signin.component';
import {SignupComponent} from './components/signup/signup.component';
import {ProfileComponent} from './components/profile/profile.component';
import {AuthGuard} from './guards/auth.guard';
import {ClanslistComponent} from './components/clanslist/clanslist.component';
import {ClanPageComponent} from './components/clan-page/clan-page.component';
import {MarketPageComponent} from './components/market/market-page/market-page.component';
import {ItemListingComponent} from './components/market/listings/item-listing/item-listing.component';
import {PlayerFactoriesComponent} from './components/player-factories/player-factories.component';
import {CurrencyListingComponent} from './components/market/listings/currency-listing/currency-listing.component';

const routes: Routes = [
    {path: '', redirectTo: 'signIn', pathMatch: 'full'},
    {path: 'signIn', component: SigninComponent},
    {path: 'signUp', component: SignupComponent},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
    {path: 'clans', component: ClanslistComponent, canActivate: [AuthGuard]},
    {path: 'clan/:id', component: ClanPageComponent, canActivate: [AuthGuard]},
    {path: 'market', component: MarketPageComponent, canActivate: [AuthGuard]},
    {path: 'item-listings', component: ItemListingComponent, canActivate: [AuthGuard]},
    {path: 'currency-listings', component: CurrencyListingComponent, canActivate: [AuthGuard]},
    {path: 'factories', component: PlayerFactoriesComponent, canActivate: [AuthGuard]}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
