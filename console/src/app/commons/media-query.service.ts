import {Injectable} from '@angular/core';

declare function jsIsMobile();
@Injectable({
  providedIn: 'root'
})
export class MediaQueryService {

  constructor() {
  }

  isMobile(): boolean {
    if (window.innerWidth <= 800 && window.innerHeight <= 600) {
      return true;
    } else {
      return jsIsMobile() || false;
    }
  }
}
