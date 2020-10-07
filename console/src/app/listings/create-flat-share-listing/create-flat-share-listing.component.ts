import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatAutocomplete, MatAutocompleteSelectedEvent} from "@angular/material/autocomplete";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {MatChipInputEvent} from "@angular/material/chips";
import {debounceTime, finalize, map, startWith, switchMap, tap} from 'rxjs/operators';
import {staticData} from "../../../environments/data";
import {DataApiServiceService} from "../../commons/data-api-service.service";
import {FileSystemDirectoryEntry, FileSystemFileEntry, NgxFileDropEntry} from "ngx-file-drop";
import {FileHolder} from "../../commons/file-holder";
import {NgxFirebaseClientService} from "@ngx-firebase/client";

@Component({
  selector: 'app-create-flat-share-listing',
  templateUrl: './create-flat-share-listing.component.html',
  styleUrls: ['./create-flat-share-listing.component.css']
})
export class CreateFlatShareListingComponent implements OnInit {

  visible = true;
  selectable = true;
  removable = true;
  isSearchingLocations = false;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  servicesFormControl = new FormControl();
  filteredServices: Observable<string[]>;

  allCurrencies: string[] = staticData.currencies;
  selectedServices: string[] = [];
  allServices: string[] = staticData.services;
  filteredCities: string[] = [];

  listingImages: FileHolder[] = [];

  formData;

  @ViewChild('servicesInput') fruitInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  constructor(private dataApiService: DataApiServiceService, private firebaseService: NgxFirebaseClientService) {
    this.filteredServices = this.servicesFormControl.valueChanges.pipe(
      startWith(null),
      map((fruit: string | null) => fruit ? this._filter(fruit) : this.allServices.slice()));
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.selectedServices.push(value.trim());
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }

    this.servicesFormControl.setValue(null);
  }

  remove(fruit: string): void {
    const index = this.selectedServices.indexOf(fruit);
    if (index >= 0) {
      this.selectedServices.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.selectedServices.push(event.option.viewValue);
    this.fruitInput.nativeElement.value = '';
    this.servicesFormControl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.allServices.filter(service => service.toLowerCase().indexOf(filterValue) === 0);
  }

  ngOnInit(): void {
    this.formData = new FormGroup({
      headline: new FormControl("", Validators.compose([
        Validators.required,
        Validators.maxLength(35),
        Validators.minLength(20),
      ])),
      location: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      dateAvailable: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      currency: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      amount: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      paymentFrequency: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      description: new FormControl("", Validators.compose([
        Validators.required,
        Validators.maxLength(160),
        Validators.minLength(100),
      ])),
      petStatus: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      smokingStatus: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      workSchedule: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      availableServices: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      minAgePreference: new FormControl("", Validators.compose([
        Validators.required,
        Validators.min(18),
        Validators.max(65),
      ])),
      maxAgePreference: new FormControl("", Validators.compose([
        Validators.required,
        Validators.min(18),
        Validators.max(65),
      ])),
      employmentStatusPreference: new FormControl("", Validators.compose([
        Validators.required,
      ])),
      petPreference: new FormControl("", Validators.compose([
        Validators.required,
      ])),
    });
    this.setUpLocationAutoComplete();

    this.listingImages.push(
      new FileHolder(this.firebaseService, null),
      new FileHolder(this.firebaseService, null),
      new FileHolder(this.firebaseService, null),
      new FileHolder(this.firebaseService, null)
    );
  }

  setUpLocationAutoComplete() {
    this.formData.get('location').valueChanges
      .pipe(
        debounceTime(300),
        tap(() => this.isSearchingLocations = true),
        switchMap(value => this.dataApiService.searchCities(value as string)
          .pipe(
            finalize(() => this.isSearchingLocations = false)
          )
        )
      ).subscribe(cities => this.filteredCities = cities);
  }

  createListing() {
    console.log(this.formData.value);
  }

  formInValid(): boolean {
    return this.formData.status == 'INVALID' || this.formData.get('minAgePreference').value > this.formData.get('maxAgePreference').value;
  }
}
