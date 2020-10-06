import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ConversationChatViewComponent} from './conversation-chat-view.component';

describe('ConversationChatViewComponent', () => {
  let component: ConversationChatViewComponent;
  let fixture: ComponentFixture<ConversationChatViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConversationChatViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConversationChatViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
