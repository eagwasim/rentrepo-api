import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BaseViewRedirectComponent} from "./commons/base-view-redirect/base-view-redirect.component";
import {FlatToShareViewComponent} from "./listings/flat-to-share-view/flat-to-share-view.component";
import {FlatmatesViewComponent} from "./listings/flatmates-view/flatmates-view.component";
import {PlaceToRentViewComponent} from "./listings/place-to-rent-view/place-to-rent-view.component";
import {TenantsViewComponent} from "./listings/tenants-view/tenants-view.component";
import {UserProfileViewComponent} from "./user/user-profile-view/user-profile-view.component";
import {ConversationBaseViewComponent} from "./messaging/conversation-base-view/conversation-base-view.component";
import {BaseViewComponent} from "./commons/base-view/base-view.component";
import {ListingsBaseViewComponent} from "./listings/listings-base-view/listings-base-view.component";
import {CreateFlatShareListingComponent} from "./listings/create-flat-share-listing/create-flat-share-listing.component";

const routes: Routes = [
  {
    path: ':languageCode',
    component: BaseViewComponent,
    children: [
      {
        path: '',
        redirectTo: 'listings/flat-to-share',
        pathMatch: 'prefix'
      },
      {
        path: 'listings',
        component: ListingsBaseViewComponent,
        children: [
          {
            path: '',
            redirectTo: 'flat-to-share',
            pathMatch: 'prefix'
          },
          {
            path: 'flat-to-share',
            component: FlatToShareViewComponent,
          },
          {
            path: 'flatmates',
            component: FlatmatesViewComponent
          },
          {
            path: 'place-to-rent',
            component: PlaceToRentViewComponent
          },
          {
            path: 'tenants',
            component: TenantsViewComponent
          },
        ]
      },
      {
        path: 'flat-to-share/create',
        component: CreateFlatShareListingComponent
      },
      {
        path: 'profile',
        component: UserProfileViewComponent
      },
      {
        path: 'messages',
        component: ConversationBaseViewComponent
      },
      {
        path: 'messages/messageID',
        component: ConversationBaseViewComponent
      },
    ]
  },
  {
    path: '**',
    component: BaseViewRedirectComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
