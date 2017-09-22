import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DefaultResponse} from '../../../model/default.response';
import {BaseService} from './base.service';
import {PokerCardDTO} from "../../../model/poker.card.dto";
import {isString} from "util";

@Injectable()
export class PlayService extends BaseService {

  constructor(private _httpClient: HttpClient) {
    super();
  }

  /**
   * Checks if there is a session available
   * @returns {Promise<Boolean>}
   */
  public isSessionAvailable(): Promise<Boolean> {

    return new Promise((resolve) => {

      this._httpClient.get(this._assembleUrl('play/available'))
        .subscribe((response: DefaultResponse<Boolean>) => {
          resolve(response.data);
        }, () => {
          resolve(false);
        });

    });

  }

  /**
   * Submits an estimate
   * @param {PokerCardDTO} pokerCard
   * @returns {Promise<Boolean>}
   */
  public submitEstimate(pokerCard: PokerCardDTO): Promise<Boolean> {

    return new Promise((resolve) => {

      this._httpClient.post(this._assembleUrl('play/submit'), pokerCard, {
        headers: this._getHeaders()
      }).subscribe((response: DefaultResponse<Boolean>) => {
        resolve(response.data);
      }, () => {
        resolve(false);
      });

    });

  }

  public newDeal(name?: string): Promise<Boolean> {

    if (!isString(name) || name.length === 0) {
      name = 'New Task';
    }

    return new Promise((resolve) => {

      this._httpClient.post(this._assembleUrl(`play/new/${encodeURI(name)}`), null, {
        headers: this._getHeaders()
      }).subscribe((response: DefaultResponse<Boolean>) => {
        resolve(response.data);
      }, () => {
        resolve(false);
      });

    });
  }
}
