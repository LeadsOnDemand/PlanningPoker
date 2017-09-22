import {Injectable} from '@angular/core';
import {BaseService} from './base.service';
import {HttpClient} from '@angular/common/http';
import {PokerConfiguration} from "../../../model/poker.configuration";
import {DefaultResponse} from "../../../model/default.response";

@Injectable()
export class ConfigurationService extends BaseService {

  constructor(private _httpClient: HttpClient) {
    super();
  }

  getConfiguration(): Promise<PokerConfiguration> {
    return new Promise((resolve) => {

      this._httpClient.get(this._assembleUrl('config'), {
        headers: this._getHeaders()
      }).subscribe((response: DefaultResponse<PokerConfiguration>) => {
          resolve(response.data);
        }, () => {
          resolve(false);
        });

    });
  }

}
