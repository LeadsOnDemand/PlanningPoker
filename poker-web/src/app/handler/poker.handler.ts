import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable()
export class PokerHandler {

  private _webSocket: WebSocket = null;

  public startGame(name: string): Promise<Boolean> {
    return new Promise((resolve) => {
      this._webSocket = new WebSocket(`ws://${environment.host}?name=${encodeURI(name)}`);
      this._webSocket.addEventListener('open', () => {
        resolve(true);
      });
    });
  }

}
