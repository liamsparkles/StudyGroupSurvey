import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { YesNoDialogComponent } from '../Components/Shared/yes-no-dialog/yes-no-dialog.component';
import { SurveyService } from '../survey.service';
import { LocalSurveyService } from '../localsurvey.service';
import { DynamicFormBuildConfig, DynamicFormConfiguration, RxDynamicFormBuilder } from '@rxweb/reactive-dynamic-forms';
import { PARM_DATA } from './form_parameter_data';
import { ReactiveFormConfig } from '@rxweb/reactive-form-validators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [ SurveyService ]
})
export class HomeComponent implements OnInit {

  serverData: any[] = PARM_DATA;

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
    if (this.dynamicForm.formGroup.valid) { 
      // if form is valid, check if the name is in the DB
      this.firstName = this.dynamicForm.formGroup.value.firstName;
      this.lastName = this.dynamicForm.formGroup.value.lastName;
      await this.surveyService.getSurveyExistance(this.firstName.toLowerCase(), this.lastName.toLowerCase())
        .then(value => {
          this.existingname = value
          console.log("Response received")
        })
        .catch(error => console.log("An error occurred"))
    }

    if (this.existingname && this.dynamicForm.formGroup.valid) { 
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
    else if (this.dynamicForm.formGroup.valid && !this.existingname) {
      // If the user's name does not exist, give them the survey/quiz
      localStorage.setItem("firstName", this.firstName.toLowerCase());
      localStorage.setItem("lastName", this.lastName.toLowerCase());
      this.route.navigate(['survey']);
    }
  }

  ngOnInit(): void {
    // Create the form and set the validation message.
    ReactiveFormConfig.set({
      validationMessage:{
        required:"This field is required"
      }
    })
    this.dynamicForm = this.dynamicFormBuilder.formGroup(this.serverData);
  }

}
