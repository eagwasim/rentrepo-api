import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DataApiServiceService {

  constructor(private httoClient: HttpClient) {
  }

  searchCities(query: string): Observable<any> {
    return this.httoClient.get('/api/v1/public/cities/search?q=' + query);
  }
}
