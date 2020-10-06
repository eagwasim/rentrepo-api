import {Component, OnInit} from '@angular/core';
import {Meta, Title} from "@angular/platform-browser";

@Component({
  selector: 'app-flatmates-view',
  templateUrl: './flatmates-view.component.html',
  styleUrls: ['./flatmates-view.component.css']
})
export class FlatmatesViewComponent implements OnInit {

  constructor(private titleService: Title, private metaService: Meta) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Find a flatmate in');
    this.metaService.updateTag(
      {
        name: 'description',
        content: 'Find a flatmate'
      }
    );
  }
}
