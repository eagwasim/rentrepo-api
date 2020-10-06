import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FlatmatesDetailViewComponent} from './flatmates-detail-view.component';

describe('FlatmatesDetailViewComponent', () => {
  let component: FlatmatesDetailViewComponent;
  let fixture: ComponentFixture<FlatmatesDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlatmatesDetailViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlatmatesDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
