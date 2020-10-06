import { TestBed } from '@angular/core/testing';

import { DataApiServiceService } from './data-api-service.service';

describe('DataApiServiceService', () => {
  let service: DataApiServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataApiServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
