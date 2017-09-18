import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment';
import {DefaultResponse} from '../../../model/default.response';

@Injectable()
export class PlayService {

  constructor(private _httpClient: HttpClient) { }

  /**
   * Assembles the URL using the specified environment URI
   *
   * @private
   *
   * @param {string} url
   * @returns {string} the assembled URL
   */
  private static _assembleUrl(url: string): string {
    return `http://${environment.host}/${environment.rsUri}/${url}`;
  }

  /**
   * Checks if there is a session available
   * @returns {Promise<Boolean>}
   */
  public isSessionAvailable(): Promise<Boolean> {

    return new Promise((resolve) => {

      this._httpClient.get(PlayService._assembleUrl('play/available'))
        .subscribe((response: DefaultResponse<Boolean>) => {
          resolve(response.data);
        }, () => {
          resolve(false);
        });

    });

  }

}
