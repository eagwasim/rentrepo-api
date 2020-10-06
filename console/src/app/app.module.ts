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
import {
  FacebookLoginProvider,
  GoogleLoginProvider,
  SocialAuthServiceConfig,
  SocialLoginModule
} from "angularx-social-login";
import {HttpClientModule} from "@angular/common/http";
import {NgxLocalStorageModule} from "ngx-localstorage";
import {NgxFirebaseClientModule} from "@ngx-firebase/client";
import {MatNativeDateModule} from "@angular/material/core";
import {FlexLayoutModule} from "@angular/flex-layout";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

const fbLoginOptions = {
  scope: 'email',
  return_scopes: true,
  enable_profile_selector: true
}; // https://developers.facebook.com/docs/reference/javascript/FB.login/v2.11

const googleLoginOptions = {
  scope: 'profile email'
}; // https://developers.google.com/api-client-library/javascript/reference/referencedocs#gapiauth2clientconfig

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
    SocialLoginModule,
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
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '15608698535-k0385jfgsibc75ghr59c7k4ok0096tgu.apps.googleusercontent.com', googleLoginOptions
            ),
          },
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider('2697031193878568', fbLoginOptions),
          },
        ],
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
