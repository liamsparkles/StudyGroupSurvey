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
    this.questionService.getQuestions().subscribe( {
      next: (q: Question[]) => this.questions = q,
      error: (e) => alert(e.message),
      complete: () => console.log("Completed Question Load"),
      });

    this.firstName = this.localSurveyService.getFirstName();
    this.lastName = this.localSurveyService.getLastName();
    this.surveyService.checkSurvey(this.firstName, this.lastName)
        .then(value => {this.score = Math.ceil(value * 100);
        console.log("done")})
        .catch(error => console.log("Score could not be calculated"))
  }

}
