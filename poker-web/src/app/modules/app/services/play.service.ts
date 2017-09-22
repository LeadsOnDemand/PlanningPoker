import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DefaultResponse} from '../../../model/default.response';
import {BaseService} from './base.service';

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

}
