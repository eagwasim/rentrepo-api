import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeServicesDetailViewComponent } from './home-services-detail-view.component';

describe('HomeServicesDetailViewComponent', () => {
  let component: HomeServicesDetailViewComponent;
  let fixture: ComponentFixture<HomeServicesDetailViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeServicesDetailViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeServicesDetailViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
