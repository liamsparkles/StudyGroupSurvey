import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom, Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { Question } from './question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private apiServerUrl = environment.apiBaseUrl + '/question';

  constructor(private http: HttpClient) { }

  public getQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.apiServerUrl}`)
  }

  public getQuestion(qId: number): Observable<Question> {
    return this.http.get<Question>(`${this.apiServerUrl}/${qId}`);
  }
}
