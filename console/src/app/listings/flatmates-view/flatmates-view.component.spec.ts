import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FlatmatesViewComponent} from './flatmates-view.component';

describe('FlatmatesViewComponent', () => {
  let component: FlatmatesViewComponent;
  let fixture: ComponentFixture<FlatmatesViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlatmatesViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlatmatesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
