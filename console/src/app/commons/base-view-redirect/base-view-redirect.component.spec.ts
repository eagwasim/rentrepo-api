import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BaseViewRedirectComponent} from './base-view-redirect.component';

describe('BaseViewRedirectComponent', () => {
  let component: BaseViewRedirectComponent;
  let fixture: ComponentFixture<BaseViewRedirectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BaseViewRedirectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BaseViewRedirectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
