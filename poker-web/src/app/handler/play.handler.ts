import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {PlayService} from '../modules/app/services/play.service';
import {isObject} from 'util';
import {PlayEvent} from "../const/play.event";
import {TokenUtil} from "../util/token.util";
import {Router} from "@angular/router";

@Injectable()
export class PlayHandler {

  private _playing: Boolean = false;
  private _webSocket: WebSocket = null;

  constructor(private _playService: PlayService, private _router: Router) {
  }

  public isPlaying() {
    return this._playing;
  }

  /**
   * Determines if there is a session available
   * @returns {Promise<Boolean>}
   */
  public isSessionAvailable(): Promise<Boolean> {
    return this._playService.isSessionAvailable();
  }

  public startGame(name: String) {

    this._webSocket = new WebSocket(`ws://${environment.host}/play/${encodeURI(name.toString())}`);
    this._webSocket.addEventListener('open', () => {
      this._playing = true;
    });

    this._webSocket.addEventListener('message', (message) => {

      this._playing = true;

      const playEvent = JSON.parse(message.data);
      this._handleMessage(playEvent);

    });

    this._webSocket.addEventListener('close', () => {
      this._playing = false;
      TokenUtil.storeToken(null);
      alert('Game has ended');

      this._router.navigate(['/login']);

    });

    this._webSocket.addEventListener('error', (error) => {
      this._playing = false;
    });

    // close out the socket connection before the page reloads/closes/etc
    window.onbeforeunload = () => {
      if (isObject(this._webSocket) && this._webSocket.readyState === WebSocket.OPEN) {
        this._webSocket.close();
      }
    };

  }

  private _handleMessage(playEvent: any) {

    if (playEvent.event === PlayEvent.SESSION_STARTED) {
      TokenUtil.storeToken(playEvent.data.token);
      this._router.navigate(['/']);
    }

  }

}
