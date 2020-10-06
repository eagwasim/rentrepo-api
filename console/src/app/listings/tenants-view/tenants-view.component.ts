import {Component, OnInit} from '@angular/core';
import {Meta, Title} from "@angular/platform-browser";

@Component({
  selector: 'app-tenants-view',
  templateUrl: './tenants-view.component.html',
  styleUrls: ['./tenants-view.component.css']
})
export class TenantsViewComponent implements OnInit {

  constructor(private titleService: Title, private metaService: Meta) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Find a tenant for your place');
    this.metaService.updateTag(
      {
        name: 'description',
        content: 'Find a tenant for your place'
      }
    );
  }
}
