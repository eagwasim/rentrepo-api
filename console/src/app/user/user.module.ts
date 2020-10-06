import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserProfileViewComponent} from './user-profile-view/user-profile-view.component';
import {MemberProfileViewComponent} from './member-profile-view/member-profile-view.component';


@NgModule({
  declarations: [UserProfileViewComponent, MemberProfileViewComponent],
  imports: [
    CommonModule
  ]
})
export class UserModule { }
