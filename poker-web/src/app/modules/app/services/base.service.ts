import {environment} from '../../../../environments/environment';
import {HttpHeaders} from '@angular/common/http';
import {TokenUtil} from "../../../util/token.util";

export abstract class BaseService {

  private static readonly AUTH_HEADER: string = 'auth';

  /**
   * Assembles the URL using the specified environment URI
   *
   * @private
   *
   * @param {string} url
   * @returns {string} the assembled URL
   */
  protected _assembleUrl(url: string): string {
    return `http://${environment.host}/${environment.rsUri}/${url}`;
  }

  protected _getHeaders(): HttpHeaders {
    return new HttpHeaders({'auth' : TokenUtil.getToken()});
  }

}
