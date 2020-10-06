import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ConversationBaseViewComponent} from './conversation-base-view/conversation-base-view.component';
import {ConversationChatViewComponent} from './conversation-chat-view/conversation-chat-view.component';


@NgModule({
  declarations: [ConversationBaseViewComponent, ConversationChatViewComponent],
  imports: [
    CommonModule
  ]
})
export class MessagingModule { }
