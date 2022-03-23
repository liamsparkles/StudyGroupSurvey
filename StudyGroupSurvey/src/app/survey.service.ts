import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom, Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { Survey } from './survey';

@Injectable({
  providedIn: 'root'
})
export class SurveyService {
  private apiServerUrl = environment.apiBaseUrl + '/survey';

  constructor(private http: HttpClient) { }

  public getSurveys(): Observable<Survey[]> {
    return this.http.get<Survey[]>(`${this.apiServerUrl}`)
  }

  public getSurvey(firstName: string, lastName: string): Observable<Survey> {
    return this.http.get<Survey>(`${this.apiServerUrl}/${firstName}/${lastName}`);
  }

  public getSurveyExistance(firstName: string, lastName: string): Promise<boolean> {
    return lastValueFrom(this.http.get<boolean>(`${this.apiServerUrl}/exists/${firstName}/${lastName}`));
  }

  public addSurvey(survey: Survey): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}`, survey);
  }

  public checkSurvey(firstName: string, lastName: string): Promise<number> {
    return lastValueFrom(this.http.get<number>(`${this.apiServerUrl}/results/${firstName}/${lastName}`))
  }

}
