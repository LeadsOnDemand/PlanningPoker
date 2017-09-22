import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {PlayService} from '../modules/app/services/play.service';
import {isArray, isObject} from 'util';
import {PlayEvent} from '../const/play.event';
import {TokenUtil} from '../util/token.util';
import {Router} from '@angular/router';
import {PokerCardDTO} from '../model/poker.card.dto';
import {GameState} from '../model/game.state';

@Injectable()
export class PlayHandler {

  private _currentPlayer: string = null;
  private _gameState: GameState = new GameState();
  private _playing: Boolean = false;
  private _webSocket: WebSocket = null;
  private _isLeader = false;

  constructor(private _playService: PlayService, private _router: Router) { }

  public get state(): GameState {
    return this._gameState;
  }

  public get showNewDealButton() {
    return isArray(this._gameState.players) && this._gameState.players.length > 1 && this._gameState.newDealReady;
  }

  public isPlaying() {
    return this._playing;
  }

  public get leader() {
    return this._isLeader;
  }

  /**
   * Determines if there is a session available
   * @returns {Promise<Boolean>}
   */
  public isSessionAvailable(): Promise<Boolean> {
    return this._playService.isSessionAvailable();
  }

  public startGame(name: string) {

    this._currentPlayer = name;

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

      this._gameState = new GameState();
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

  public submitEstimate(pokerCard: PokerCardDTO) {
    this._playService.submitEstimate(pokerCard);
  }

  public newDeal() {
    if (this._gameState.showCards) {
      this._playService.newDeal();
    }
  }

  private _handleMessage(playEvent: any) {

    if (playEvent.event === PlayEvent.SESSION_STARTED) {
      this._isLeader = playEvent.data.leader;
      TokenUtil.storeToken(playEvent.data.token);
      this._router.navigate(['/']);
    } else {
      this._gameState = playEvent.state;
    }

  }

  public isCurrentPlayer(user: string): boolean {
    return user === this._currentPlayer;
  }

}
