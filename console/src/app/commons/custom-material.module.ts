import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ErrorStateMatcher, ShowOnDirtyErrorStateMatcher} from "@angular/material/core";

import {MAT_SNACK_BAR_DATA, MatSnackBarModule} from "@angular/material/snack-bar";

import {MAT_DIALOG_DEFAULT_OPTIONS} from "@angular/material/dialog";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatTabsModule} from "@angular/material/tabs";
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {NgxFlagIconCssModule} from "ngx-flag-icon-css";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {MatDividerModule} from "@angular/material/divider";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatSelectModule} from "@angular/material/select";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatCardModule} from "@angular/material/card";
import {ScrollingModule} from '@angular/cdk/scrolling';
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatGridListModule} from "@angular/material/grid-list";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatChipsModule} from "@angular/material/chips";


@NgModule({
  imports: [
    CommonModule,
    MatToolbarModule,
    MatTabsModule,
    MatMenuModule,
    MatButtonModule,
    NgxFlagIconCssModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatListModule,
    MatDividerModule,
    MatAutocompleteModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatSelectModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatCardModule,
    ScrollingModule,
    MatTooltipModule,
    MatDatepickerModule,
    MatGridListModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatChipsModule
  ],
  exports: [
    MatToolbarModule,
    MatTabsModule,
    MatMenuModule,
    MatButtonModule,
    NgxFlagIconCssModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatListModule,
    MatDividerModule,
    MatAutocompleteModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatSelectModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatCardModule,
    ScrollingModule,
    MatTooltipModule,
    MatDatepickerModule,
    MatGridListModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatChipsModule
  ],
  declarations: [],

  providers: [
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher},
    {provide: MAT_SNACK_BAR_DATA, useValue: {duration: 2500}}
  ]
})
export class CustomMaterialModule {
}
