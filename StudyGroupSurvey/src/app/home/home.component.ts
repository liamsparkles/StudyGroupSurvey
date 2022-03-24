import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { YesNoDialogComponent } from '../Components/Shared/yes-no-dialog/yes-no-dialog.component';
import { SurveyService } from '../survey.service';
import { LocalSurveyService } from '../localsurvey.service';
import { DynamicFormBuildConfig, DynamicFormConfiguration, RxDynamicFormBuilder } from '@rxweb/reactive-dynamic-forms';
import { ReactiveFormConfig } from '@rxweb/reactive-form-validators';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [ SurveyService ]
})
export class HomeComponent implements OnInit {

  firstFormControl = new FormControl('', [Validators.required]);
  lastFormControl = new FormControl('', [Validators.required]);


  dynamicForm !: DynamicFormBuildConfig;
  dynamicFormConfiguration !: DynamicFormConfiguration;
  
  constructor(private route:Router, 
              public dialog: MatDialog, 
              private surveyService: SurveyService, 
              private localSurveyService: LocalSurveyService,
              private dynamicFormBuilder:RxDynamicFormBuilder) {}
  firstName: string = "";
  lastName: string = "";
  existingname: boolean = true; // whether the name exists
  uiBindings:string[] = ["firstName", "lastName"]; // for the dynamic form

  async startSurvey() {
    this.firstName = this.firstFormControl.value;
    this.lastName = this.lastFormControl.value;
    await this.surveyService.getSurveyExistance(this.firstName.toLowerCase(), this.lastName.toLowerCase())
      .then(value => {
        this.existingname = value
        console.log("Response received")
      })
      .catch(error => console.log("An error occurred"))

    if (this.existingname) {
      // If the user's name exists, prompt user to check their results
      const dialogRef = this.dialog.open(YesNoDialogComponent, {
      maxWidth: "400px",
      data: {
        title: "Survey Already Completed",
        message: "Do you want to see your results?"}
      });
      // act on the response
      dialogRef.afterClosed().subscribe(dialogResult => { 
        if (dialogResult) { // They said yes, go to results, persist the user's name 
          localStorage.setItem("firstName", this.firstName.toLowerCase());
          localStorage.setItem("lastName", this.lastName.toLowerCase());
          this.route.navigate(['results']);
        }
      })
    }
    else if (!this.existingname) {
      // If the user's name does not exist, give them the survey/quiz
      localStorage.setItem("firstName", this.firstName.toLowerCase());
      localStorage.setItem("lastName", this.lastName.toLowerCase());
      this.route.navigate(['survey']);
    }
  }

  ngOnInit(): void {
    // Create the form and set the validation message.
  }

}
