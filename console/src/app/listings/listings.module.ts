import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FlatToShareViewComponent} from './flat-to-share-view/flat-to-share-view.component';
import {FlatToShareDetailViewComponent} from './flat-to-share-detail-view/flat-to-share-detail-view.component';
import {PlaceToRentViewComponent} from './place-to-rent-view/place-to-rent-view.component';
import {PlaceToRentDetailViewComponent} from './place-to-rent-detail-view/place-to-rent-detail-view.component';
import {FlatmatesViewComponent} from './flatmates-view/flatmates-view.component';
import {FlatmatesDetailViewComponent} from './flatmates-detail-view/flatmates-detail-view.component';
import {TenantsDetailViewComponent} from './tenants-detail-view/tenants-detail-view.component';
import {TenantsViewComponent} from './tenants-view/tenants-view.component';
import {ListingsBaseViewComponent} from './listings-base-view/listings-base-view.component';
import {RouterModule} from "@angular/router";
import {CustomMaterialModule} from "../commons/custom-material.module";
import {CreateFlatShareListingComponent} from './create-flat-share-listing/create-flat-share-listing.component';
import {A11yModule} from "@angular/cdk/a11y";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxFileDropModule} from "ngx-file-drop";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";


@NgModule({
  declarations: [
    ListingsBaseViewComponent,
    FlatToShareViewComponent,
    FlatToShareDetailViewComponent,
    PlaceToRentViewComponent,
    PlaceToRentDetailViewComponent,
    FlatmatesViewComponent,
    FlatmatesDetailViewComponent,
    TenantsDetailViewComponent,
    TenantsViewComponent,
    CreateFlatShareListingComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    CustomMaterialModule,
    A11yModule,
    FormsModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,
  ]
})
export class ListingsModule {
}
