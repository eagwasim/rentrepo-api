import {Component, NgZone, OnInit} from '@angular/core';
import {MediaQueryService} from "../media-query.service";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {AccessService} from "../access.service";
import {LocalStorageService} from "ngx-localstorage";
import {CdkScrollable, ScrollDispatcher} from "@angular/cdk/overlay";
import {NgxFirebaseClientService} from "@ngx-firebase/client";
import {staticData} from "../../../environments/data";
import {debounceTime, finalize, switchMap, tap} from "rxjs/operators";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DataApiServiceService} from "../data-api-service.service";

@Component({
  selector: 'app-base-view',
  templateUrl: './base-view.component.html',
  styleUrls: ['./base-view.component.css']
})
export class BaseViewComponent implements OnInit {
  sideNavMode = 'over';
  sideNaveOpen = false;
  activeLink;
  selectedLanguage;
  supportedLanguages;
  supportedLanguagesList;
  isSearchingLocations = false;
  isLoading = false;
  user;

  scrollTop = 0;
  elevateNav = false;

  constructor(
    private mediaQuery: MediaQueryService,
    private router: Router,
    private storage: LocalStorageService,
    private route: ActivatedRoute,
    private accessService: AccessService,
    private scrollDispatcher: ScrollDispatcher,
    private firebase: NgxFirebaseClientService,
    private dataApiService: DataApiServiceService,
    private zone: NgZone) {
  }

  ngOnInit(): void {
    this.activeLink = this.router.url;
    this.supportedLanguages = staticData.supportedLanguages;
    this.supportedLanguagesList = Object.entries(this.supportedLanguages).sort((a, b) => b[0].localeCompare(a[0]));

    this.route.paramMap.subscribe(params => {
      let language = params.get("languageCode");
      if (language == undefined || this.supportedLanguages[language] == undefined) {
        this.router.navigate(['']);
      } else {
        this.selectedLanguage = language;
        this.firebase.auth().languageCode = language;
        this.storage.set('x-lang', language);
      }
    });

    this.router.events.subscribe((val) => {
      if (val instanceof NavigationEnd) {
        this.activeLink = val.url;
      }
    });
    this.user = this.storage.get('x-user');
    this.scrollDispatcher.scrolled().subscribe((cdk: CdkScrollable) => {
      this.zone.run(() => {
        //Here you can add what to happen when scroll changed
        //I want to display the scroll position for example
        const scrollPosition = cdk.getElementRef().nativeElement.scrollTop;
        this.elevateNav = scrollPosition > 30;
      });
    });
  }

  isMobile(): boolean {
    return this.mediaQuery.isMobile();
  }

  signIn(provider) {
    let self = this;
    this.isLoading = true;

    this.firebase.auth().signInWithPopup(provider).then(function (result) {
      // The signed-in user info.
      result.user.getIdToken(false).then((token) => {
        self.accessService.getUserByFirebaseToken(token).subscribe(response => {
          console.log(response);
          self.firebase.auth().currentUser.delete();
          self.firebase.auth().signInWithCustomToken(response['fireBaseToken']).then((user) => {
            self.isLoading = false;
            self.user = response['user'];
            self.storage.set('x-user', response['user']);
            self.storage.set('x-user-token', response['token']);
          }).catch((error) => {
            console.log(error);
            self.isLoading = false;
          })
        }, error1 => {
          self.isLoading = false;
        })
      }).catch((error) => {
        self.isLoading = false;
      });
      // ...
    }).catch(function (error) {
      // Handle Errors here.
      var errorCode = error.code;
      var errorMessage = error.message;
      // The email of the user's account used.
      var email = error.email;
      // The firebase.auth.AuthCredential type that was used.
      var credential = error.credential;
      self.isLoading = false;

      console.log(error);
      // ...
    });

  }

  signInWithGoogle(): void {
    let googleAuthProvider = new this.firebase.firebase.auth.GoogleAuthProvider();
    this.signIn(googleAuthProvider);
    // this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

  signInWithFB(): void {
    let facebookAuthProvider = new this.firebase.firebase.auth.FacebookAuthProvider();
    this.signIn(facebookAuthProvider);
  }

  signInWithGitHub(): void {
    let githubAuthProvider = new this.firebase.firebase.auth.GithubAuthProvider();
    this.signIn(githubAuthProvider);
  }

  signInWithTwitter(): void {
    let twitterAuthProvider = new this.firebase.firebase.auth.TwitterAuthProvider();
    this.signIn(twitterAuthProvider);
  }

  signInWithEmail(): void {
  }


  signOut(): void {
    this.storage.remove('x-user');
    this.user = null;
    try {
      this.firebase.auth().signOut();
    } catch (e) {
    }
    location.href = '/';
  }

  routeForLanguage(langCode: string) {
    return this.activeLink.replace('/' + this.selectedLanguage + '/', '/' + langCode + '/');
  }
}
