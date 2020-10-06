import {Component, OnInit} from '@angular/core';
import {Meta, Title} from "@angular/platform-browser";

@Component({
  selector: 'app-place-to-rent-view',
  templateUrl: './place-to-rent-view.component.html',
  styleUrls: ['./place-to-rent-view.component.css']
})
export class PlaceToRentViewComponent implements OnInit {

  constructor(private titleService: Title, private metaService: Meta) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Find a place to rent');
    this.metaService.updateTag(
      {
        name: 'description',
        content: 'Find a place to rent in ' + location['name']
      }
    );
  }
}
