import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AuthenticatedNavOptionsComponent} from './authenticated-nav-options.component';

describe('AuthenticatedNavOptionsComponent', () => {
  let component: AuthenticatedNavOptionsComponent;
  let fixture: ComponentFixture<AuthenticatedNavOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthenticatedNavOptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthenticatedNavOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
