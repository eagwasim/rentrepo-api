import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LocalStorageService} from "ngx-localstorage";

@Component({
  selector: 'app-base-view-redirect',
  templateUrl: './base-view-redirect.component.html',
  styleUrls: ['./base-view-redirect.component.css']
})
export class BaseViewRedirectComponent implements OnInit {

  constructor(private storage: LocalStorageService, private router: Router) {
  }

  ngOnInit(): void {
    this.router.navigate(['en-GB', 'listings', 'flat-to-share']).finally();
  }

}
