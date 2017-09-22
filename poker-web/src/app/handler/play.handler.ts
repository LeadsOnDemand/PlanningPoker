import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {PlayService} from '../modules/app/services/play.service';
import {isObject} from 'util';
import {PlayEvent} from '../const/play.event';
import {TokenUtil} from '../util/token.util';
import {Router} from '@angular/router';
import {Player} from '../model/player';
import {PokerCardDTO} from "../model/poker.card.dto";

@Injectable()
export class PlayHandler {

  private _players: Player[];
  private _playing: Boolean = false;
  private _showCards: Boolean = true;
  private _webSocket: WebSocket = null;

  constructor(private _playService: PlayService, private _router: Router) { }

  public get players(): Player[] {
    return this._players;
  }

  public get showCards(): Boolean {
    return this._showCards;
  }

  public get showNewDealButton() {
    return this._players.length > 1 && this._showCards === true;
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

  public startGame(name: string) {

    const player = new Player();
    player.name = name;
    player.isCurrent = true;

    this._players = [player];

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

  public submitEstimate(pokerCard: PokerCardDTO) {
    this._playService.submitEstimate(pokerCard);
  }

  public newDeal() {
    if (this._showCards) {
      this._playService.newDeal();
    }
  }

  private _handleMessage(playEvent: any) {

    if (playEvent.event === PlayEvent.SESSION_STARTED) {
      TokenUtil.storeToken(playEvent.data.token);
      this._router.navigate(['/']);
    } else if (playEvent.event === PlayEvent.PLAYER_JOINED) {
      this._addPlayer(playEvent.user);
    } else if (playEvent.event === PlayEvent.ESTIMATE_SUBMITTED) {
      this._setPlayerCard(playEvent.user, playEvent.data);
    } else if (playEvent.event === PlayEvent.PLAYER_QUIT) {
      this._removePlayer(playEvent.user);
    } else if (playEvent.event === PlayEvent.NEW_DEAL) {

      this._showCards = false;

      const count = this._players.length;
      for (let i = 0; i < count; i++) {
        this._players[i].currentCard = null;
      }

    } else if (playEvent.event === PlayEvent.SHOW_CARDS) {
      this._showCards = true;
    }

  }

  private _addPlayer(user: string) {

    if (!this._isCurrentPlayer(user) && this._getPlayer(user) === null) {

      const player = new Player();
      player.name = user;
      player.isCurrent = false;

      this._players.push(player);

    }

  }

  private _removePlayer(user: string) {

    const count = this._players.length;
    for (let i = 0; i < count; i++) {
      if (this._players[i].name === user) {
        delete this._players[i];
        break;
      }
    }

  }

  private _isCurrentPlayer(user: string): boolean {
    const player = this._getPlayer(user);
    return (player !== null && player.isCurrent);
  }

  private _setPlayerCard(user: string, data: any) {
    for (let key in data) {
      if (data.hasOwnProperty(key)) {
        this._getPlayer(key).currentCard = data[key];
      }
    }
  }

  private _getPlayer(name: string): Player {

    const count = this._players.length;
    for (let i = 0; i < count; i++) {
      if (this._players[i].name === name) {
        return this._players[i];
      }
    }

    return null;

  }

}
