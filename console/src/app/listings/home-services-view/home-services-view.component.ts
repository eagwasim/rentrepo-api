import {Component, OnInit} from '@angular/core';
import {Meta, Title} from "@angular/platform-browser";

@Component({
  selector: 'app-home-services-view',
  templateUrl: './home-services-view.component.html',
  styleUrls: ['./home-services-view.component.css']
})
export class HomeServicesViewComponent implements OnInit {

  constructor(private titleService: Title, private metaService: Meta) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Find a service person for your home');
    this.metaService.updateTag(
      {
        name: 'description',
        content: 'Find plumbers, gardeners, dog walkers, electricians, cleaners and other home service personnel',
      }
    );
  }
}
