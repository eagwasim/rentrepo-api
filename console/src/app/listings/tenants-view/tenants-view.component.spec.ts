import {ComponentFixture, TestBed} from '@angular/core/testing';

import {TenantsViewComponent} from './tenants-view.component';

describe('TenantsViewComponent', () => {
  let component: TenantsViewComponent;
  let fixture: ComponentFixture<TenantsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TenantsViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
