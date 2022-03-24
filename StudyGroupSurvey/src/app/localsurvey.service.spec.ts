import { TestBed } from '@angular/core/testing';

import { LocalSurveyService } from './localsurvey.service';

describe('LocalsurveyService', () => {
  let service: LocalSurveyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalSurveyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
