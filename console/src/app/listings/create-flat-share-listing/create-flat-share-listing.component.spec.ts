import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateFlatShareListingComponent} from './create-flat-share-listing.component';

describe('CreateFlatShareListingComponent', () => {
  let component: CreateFlatShareListingComponent;
  let fixture: ComponentFixture<CreateFlatShareListingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateFlatShareListingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFlatShareListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
