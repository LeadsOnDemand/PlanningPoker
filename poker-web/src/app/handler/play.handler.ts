import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {PlayService} from '../modules/app/services/play.service';
import {isObject} from "util";

@Injectable()
export class PlayHandler {

  private _playing: Boolean = false;
  private _webSocket: WebSocket = null;

  constructor(private _playService: PlayService) { }

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

  public startGame(name: String): Promise<Boolean> {
    return new Promise((resolve) => {

      this._webSocket = new WebSocket(`ws://${environment.host}/play/${encodeURI(name.toString())}`);
      this._webSocket.addEventListener('open', () => {
        this._playing = true;
        resolve(true);
      });

      this._webSocket.addEventListener('message', (message) => {
        this._playing = true;
        console.dir(message.data);
      });

      this._webSocket.addEventListener('close', () => {
        this._playing = false;
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

    });
  }

}
