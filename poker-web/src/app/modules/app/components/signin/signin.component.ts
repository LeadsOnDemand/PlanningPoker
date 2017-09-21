import {Component} from '@angular/core';
import {PlayHandler} from '../../../../handler/play.handler';
import {KeyCodes} from '../../../../const/key.codes';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent {

  private _name: String = '';

  constructor(private _router: Router, private _playHandler: PlayHandler) { }

  /**
   * Handles the key down event for ENTER key
   * @param {KeyboardEvent} $event
   * @private
   */
  _onKeyDown($event: KeyboardEvent) {
    if ($event.keyCode === KeyCodes.ENTER) {
      this._onJoin();
    }
  }

  /**
   * Attempts to join the game
   * @private
   */
  _onJoin() {
    this._playHandler.startGame(this._name).then(() => {
      this._router.navigate(['/']);
    });
  }

}
