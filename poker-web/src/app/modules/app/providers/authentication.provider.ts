import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {PlayHandler} from '../../../handler/play.handler';

@Injectable()
export class AuthenticationProvider implements CanActivate {

  constructor(private router: Router, private _playHandler: PlayHandler) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {

    const currentState = this._playHandler.isPlaying();
    if (!currentState) {
      this._showLogin();
    }

    return Promise.resolve(currentState);

  }

  /**
   * Navigates to the login screen
   * @private
   */
  private _showLogin(): void {
    this.router.navigate(['/login']);
  }

}
