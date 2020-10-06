import {ComponentFixture, TestBed} from '@angular/core/testing';

import {TenantsDetailViewComponent} from './tenants-detail-view.component';

describe('TenantsDetailViewComponent', () => {
  let component: TenantsDetailViewComponent;
  let fixture: ComponentFixture<TenantsDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TenantsDetailViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantsDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
