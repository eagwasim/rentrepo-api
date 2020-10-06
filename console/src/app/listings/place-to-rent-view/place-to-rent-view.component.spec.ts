import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PlaceToRentViewComponent} from './place-to-rent-view.component';

describe('PlaceToRentViewComponent', () => {
  let component: PlaceToRentViewComponent;
  let fixture: ComponentFixture<PlaceToRentViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlaceToRentViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlaceToRentViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
