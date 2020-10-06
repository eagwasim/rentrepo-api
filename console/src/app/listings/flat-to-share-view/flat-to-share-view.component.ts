import {Component, OnInit} from '@angular/core';
import {Meta, Title} from "@angular/platform-browser";

@Component({
  selector: 'app-flat-to-share-view',
  templateUrl: './flat-to-share-view.component.html',
  styleUrls: ['./flat-to-share-view.component.css']
})
export class FlatToShareViewComponent implements OnInit {

  constructor(private titleService: Title, private metaService: Meta) {

  }

  ngOnInit(): void {
    this.titleService.setTitle('Find shared flats');
    this.metaService.updateTag(
      {
        name: 'description',
        content: 'Find shared flats'
      }
    );
  }
}
