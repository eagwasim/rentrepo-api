import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BaseViewComponent} from './base-view/base-view.component';
import {AuthenticatedNavOptionsComponent} from './authenticated-nav-options/authenticated-nav-options.component';
import {UnauthenticatedNavOptionsComponent} from './unauthenticated-nav-options/unauthenticated-nav-options.component';
import {BaseViewRedirectComponent} from './base-view-redirect/base-view-redirect.component';
import {RouterModule} from "@angular/router";
import {CustomMaterialModule} from "./custom-material.module";
import {NgxFontAwesomeModule} from "ngx-font-awesome";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {A11yModule} from "@angular/cdk/a11y";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [BaseViewComponent, AuthenticatedNavOptionsComponent, UnauthenticatedNavOptionsComponent, BaseViewRedirectComponent],
  imports: [
    CommonModule,
    RouterModule,
    CustomMaterialModule,
    NgxFontAwesomeModule,
    MatProgressSpinnerModule,
    A11yModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class CommonsModule {
}
