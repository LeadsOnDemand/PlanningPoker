export class TokenUtil {

  private static readonly TOKEN_KEY = 'jwt_token';

  public static storeToken(token: string) {
    localStorage.setItem(TokenUtil.TOKEN_KEY, token);
  }

  public static getToken(): string {
    return localStorage.getItem(TokenUtil.TOKEN_KEY);
  }

}
