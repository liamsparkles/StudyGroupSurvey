import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LocalSurveyService } from '../localsurvey.service';
import { Question } from '../question';
import { QuestionService } from '../question.service';
import { SurveyService } from '../survey.service';

@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.scss'],
})
export class SurveyComponent implements OnInit{
  isValid: boolean = false;
  numButtons: number = 0;
  questionIds: {qId: string, valid: boolean}[] = [];
  public questions: Question[] = [];

  productFormGroup!: FormGroup;
  constructor(private route:Router, 
              public formBuilder: FormBuilder, 
              private localSurveyService: LocalSurveyService, 
              private surveyService: SurveyService, 
              private questionService: QuestionService) {
  }
  questionForm = this.formBuilder.group({})

  async submitResponses(form: NgForm) {
    // Save the survey response from the user
    if (form.valid) {
      await this.surveyService.addSurvey(this.localSurveyService.getSurvey())
        .then(q => {
          console.log(q)
          console.log("Pushed")
        })
        .catch(e => alert(e.message)); // Push data to server
      this.route.navigate(['results']);
    }
    else {
      console.log("The form is not valid");
    }
  }

  submitRadio(qId: string, rId: number, rText: string){
    // Save the response into local storage
    this.localSurveyService.addResult(qId, rId);
  
    for (let questionId of this.questionIds) {
      if (qId == questionId.qId && questionId.valid == false) {
        this.numButtons += 1;
        questionId.valid = true;
        break;
      }
    }
    if (this.numButtons >= this.questions.length) { this.isValid = true; }
  }

  ngOnInit() {
    // Get the questions from the DB
    this.questionService.getQuestions().subscribe( {
      next: (q: Question[]) => this.questions = q,
      error: (e) => alert(e.message),
      complete: () => {console.log("Completed Question Load")
      for (let qId of this.questions) { 
        this.questionIds.push({qId: qId.questionId, valid: false})
      }
      }});

    // Load names (our ids) and save them into our local service in case we reloaded the page.
    let firstName: string = localStorage.getItem("firstName") || "";
    let lastName: string = localStorage.getItem("lastName") || "";
    this.localSurveyService.updateNames(firstName, lastName);
  }

}