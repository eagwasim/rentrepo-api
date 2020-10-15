import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CommonsModule} from "./commons/commons.module";
import {CustomMaterialModule} from "./commons/custom-material.module";
import {ListingsModule} from "./listings/listings.module";
import {MessagingModule} from "./messaging/messaging.module";
import {SubscriptionModule} from "./subscription/subscription.module";
import {UserModule} from "./user/user.module";
import {HttpClientModule} from "@angular/common/http";
import {NgxLocalStorageModule} from "ngx-localstorage";
import {NgxFirebaseClientModule} from "@ngx-firebase/client";
import {MatNativeDateModule} from "@angular/material/core";
import {FlexLayoutModule} from "@angular/flex-layout";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    NgxLocalStorageModule.forRoot(),
    CustomMaterialModule,
    ListingsModule,
    MessagingModule,
    SubscriptionModule,
    UserModule,
    CommonsModule,
    AppRoutingModule,
    MatNativeDateModule,
    NgxFirebaseClientModule.forRoot({
      apiKey: "AIzaSyAgUzXsc16TpDL4DOiJk9WMxJG4ZI4z00Y",
      authDomain: "rentrepo.firebaseapp.com",
      databaseURL: "https://rentrepo.firebaseio.com",
      projectId: "rentrepo",
      storageBucket: "rentrepo.appspot.com",
      messagingSenderId: "15608698535",
      appId: "1:15608698535:web:64bd31b304cba48e27ed82",
      measurementId: "G-QRC4F0LF04"
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
