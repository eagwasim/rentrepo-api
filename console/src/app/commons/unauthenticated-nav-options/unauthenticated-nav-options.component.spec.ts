import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UnauthenticatedNavOptionsComponent} from './unauthenticated-nav-options.component';

describe('UnauthenticatedNavOptionsComponent', () => {
  let component: UnauthenticatedNavOptionsComponent;
  let fixture: ComponentFixture<UnauthenticatedNavOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthenticatedNavOptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthenticatedNavOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
