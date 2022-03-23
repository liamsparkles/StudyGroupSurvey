import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LocalSurveyService } from '../localsurvey.service';
import { Question } from '../question';
import { QuestionService } from '../question.service';
import { Survey } from '../survey';
import { SurveyService } from '../survey.service';

@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.scss'],
})
export class SurveyComponent implements OnInit{
  isSubmitted = false;
  public questions: Question[] = [];
  private tempSurvey: Survey = new Survey("", "", []);

  productFormGroup!: FormGroup;
  constructor(private route:Router, public formBuilder: FormBuilder, private localSurveyService: LocalSurveyService, private surveyService: SurveyService, private questionService: QuestionService) {
  }
  
  submitResponses(form: NgForm) {
    if (form.valid) {
      //TODO Check for null first/last name, how to fix that?
      this.pushToServer();
      console.log("Pushed");
      this.route.navigate(['results']);
    }
    else {
      console.log("The form is not valid");
    }
  }

  submitRadio(qId: number, rId: number, rText: string){
    console.log(rText);
    this.localSurveyService.addResult(qId, rId);
  }

  printLocalDB(){
    let fName = this.localSurveyService.getFirstName();
    let lName = this.localSurveyService.getLastName();
    console.log(fName);
    console.log(lName);
  }

  pushToServer() {
    this.surveyService.addSurvey(this.localSurveyService.getSurvey()).subscribe( {
      next: (q => console.log(q)),
      error: (e) => alert(e.message)
    });
  }

  ngOnInit() {
    this.questionService.getQuestions().subscribe( {
      next: (q: Question[]) => this.questions = q,
      error: (e) => alert(e.message),
      complete: () => console.log("Completed Question Load"),
      });

    this.surveyService.getSurvey("Liam", "Sparling").subscribe( {
      next: (q => this.tempSurvey = q),
      error: (e) => alert(e.message)
    });
  }


}
