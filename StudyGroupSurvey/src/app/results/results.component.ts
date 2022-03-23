import { Component, OnInit } from '@angular/core';
import { LocalSurveyService } from '../localsurvey.service';
import { Question } from '../question';
import { QuestionService } from '../question.service';
import { SurveyService } from '../survey.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.scss'],
  providers: [ SurveyService ]
})
export class ResultsComponent implements OnInit {
  questions: Question[] = [];
  score: number = 0;
  firstName: string = "";
  lastName: string = "";

  constructor(private localSurveyService: LocalSurveyService, private surveyService: SurveyService, private questionService: QuestionService) { }

  ngOnInit() {
    // Load the user's name and check their survey results, store the result in 'score'
    this.firstName = localStorage.getItem("firstName") || "";
    this.lastName = localStorage.getItem("lastName") || "";
    this.localSurveyService.updateNames(this.firstName, this.lastName);
    this.surveyService.checkSurvey(this.firstName, this.lastName)
        .then(value => {this.score = Math.ceil(value * 100);
        console.log("done")})
        .catch(error => console.log("Score could not be calculated"))
  }

}
