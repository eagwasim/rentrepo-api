import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FlatToShareViewComponent} from './flat-to-share-view.component';

describe('FlatToShareViewComponent', () => {
  let component: FlatToShareViewComponent;
  let fixture: ComponentFixture<FlatToShareViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlatToShareViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlatToShareViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
