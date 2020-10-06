import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ConversationBaseViewComponent} from './conversation-base-view.component';

describe('ConversationBaseViewComponent', () => {
  let component: ConversationBaseViewComponent;
  let fixture: ComponentFixture<ConversationBaseViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConversationBaseViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConversationBaseViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
