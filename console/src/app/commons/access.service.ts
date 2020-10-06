import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccessService {

  constructor(private httpClient: HttpClient) {
  }

  getUserByToken(token: String, provider: String): Observable<any> {
    return this.httpClient.get('/api/v1/access/oauth/' + provider + '?token=' + token);
  }

  getUserByFirebaseToken(token: String): Observable<any> {
    return this.httpClient.get('/api/v1/access/oauth/firebase' + '?token=' + token);
  }
}
