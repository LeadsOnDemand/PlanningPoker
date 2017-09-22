import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {SigninComponent} from './components/signin/signin.component';
import {PlayBoardComponent} from './components/play-board/play-board.component';
import {SelectivePreloadingStrategy} from '../../classes/selective.preloading.strategy';
import {AuthenticationProvider} from './providers/authentication.provider';

const appRoutes: Routes = [{
  path: '',
  component: PlayBoardComponent,
  canActivate: [AuthenticationProvider]
}, {
  path: 'login',
  component: SigninComponent
}];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes, {
        preloadingStrategy: SelectivePreloadingStrategy
      }
    )
  ],
  exports: [
    RouterModule
  ],
  providers: [
    SelectivePreloadingStrategy
  ]
})
export class AppRoutingModule { }
