import { Injectable } from '@angular/core';
import { Survey } from './survey';

@Injectable({
  providedIn: 'root'
})
export class LocalSurveyService {
  private survey: Survey = new Survey("", "", []);

  constructor() { }

  updateNames(firstName: string, lastName: string) {
    this.survey.setName(firstName, lastName);
  }

  addResult(questionId: number, responseId: number) {
    for (let result of this.survey.getResults()) {
      if (result.qId == questionId) {
        result.rId = responseId;  // modify referenced data
        return;
      }
    }
    this.survey.addResult(questionId, responseId);
  }

  getNames() {
    return this.survey.getName();
  }

  getFirstName() {
    let name = this.survey.getName();
    return name.firstName;
  }

  getLastName() {
    let name = this.survey.getName();
    return name.lastName;
  }

  getSurvey() {
    return this.survey;
  }
}
