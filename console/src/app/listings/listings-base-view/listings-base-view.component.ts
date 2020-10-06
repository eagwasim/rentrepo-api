import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MediaQueryService} from "../../commons/media-query.service";
import {LocalStorageService} from "ngx-localstorage";

@Component({
  selector: 'app-listings-base-view',
  templateUrl: './listings-base-view.component.html',
  styleUrls: ['./listings-base-view.component.css']
})
export class ListingsBaseViewComponent implements OnInit {
  activeLink;
  selectedLanguage;

  constructor(private router: Router, private storage: LocalStorageService, private mediaQuery: MediaQueryService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activeLink = this.router.url;
    this.route.parent.paramMap.subscribe(params => {
      this.selectedLanguage = params.get("languageCode");
    });
  }

  isMobile(): boolean {
    return this.mediaQuery.isMobile();
  }
}

