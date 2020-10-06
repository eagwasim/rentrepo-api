import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ListingsBaseViewComponent} from './listings-base-view.component';

describe('ListingsBaseViewComponent', () => {
  let component: ListingsBaseViewComponent;
  let fixture: ComponentFixture<ListingsBaseViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListingsBaseViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListingsBaseViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
