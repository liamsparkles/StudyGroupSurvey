import { TestBed } from '@angular/core/testing';

import { LocalsurveyService } from './localsurvey.service';

describe('LocalsurveyService', () => {
  let service: LocalsurveyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalsurveyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
