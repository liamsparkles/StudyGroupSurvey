import { Component, HostListener, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
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
  isSubmitted = false;
  public questions: Question[] = [];

  productFormGroup!: FormGroup;
  constructor(private route:Router, 
              public formBuilder: FormBuilder, 
              private localSurveyService: LocalSurveyService, 
              private surveyService: SurveyService, 
              private questionService: QuestionService) {
  }

  // @HostListener("window:beforeunload", ["$event"]) unloadHandler(event: Event) {
  //   console.log("Processing beforeunload..."), 
  //   localStoddrage.setItem("firstName", this.localSurveyService.getFirstName()),
  //   localStorage.setItem("loadName", this.localSurveyService.getLastName());
  // } 
  async submitResponses(form: NgForm) {
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

  ngOnInit() {
    this.questionService.getQuestions().subscribe( {
      next: (q: Question[]) => this.questions = q,
      error: (e) => alert(e.message),
      complete: () => console.log("Completed Question Load"),
      });

    let firstName: string = localStorage.getItem("firstName") || "";
    let lastName: string = localStorage.getItem("lastName") || "";
    this.localSurveyService.updateNames(firstName, lastName);
    this.printLocalDB();
  }


}