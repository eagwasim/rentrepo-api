import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PlaceToRentDetailViewComponent} from './place-to-rent-detail-view.component';

describe('PlaceToRentDetailViewComponent', () => {
  let component: PlaceToRentDetailViewComponent;
  let fixture: ComponentFixture<PlaceToRentDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlaceToRentDetailViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlaceToRentDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
