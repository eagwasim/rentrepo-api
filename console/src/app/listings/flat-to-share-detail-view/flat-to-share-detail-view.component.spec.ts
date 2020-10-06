import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FlatToShareDetailViewComponent} from './flat-to-share-detail-view.component';

describe('FlatToShareDetailViewComponent', () => {
  let component: FlatToShareDetailViewComponent;
  let fixture: ComponentFixture<FlatToShareDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlatToShareDetailViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlatToShareDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
