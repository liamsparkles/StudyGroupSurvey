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
  validparameters: boolean = true;
  existingname: boolean = true;

  uiBindings:string[] = ["firstName", "lastName"];

  async startSurvey() {
    //this.checkName(this.firstname, this.lastname);
    if (this.dynamicForm.formGroup.valid) {
      this.firstName = this.dynamicForm.formGroup.value.firstName;
      this.lastName = this.dynamicForm.formGroup.value.lastName;
      await this.surveyService.getSurveyExistance(this.firstName, this.lastName)
        .then(value => {
          this.existingname = value
          console.log("done")
        })
        .catch(error => console.log("Much error"))
    }
    console.log(this.existingname);

    if (this.existingname && this.dynamicForm.formGroup.valid) {
      const dialogRef = this.dialog.open(YesNoDialogComponent, {
      maxWidth: "400px",
      data: {
        title: "Survey Already Completed",
        message: "Do you want to see your results?"}
      });
      // listen to response
      dialogRef.afterClosed().subscribe(dialogResult => { 
        if (dialogResult) {
          this.localSurveyService.updateNames(this.firstName, this.lastName);
          this.route.navigate(['results']);
        }
      })
    }
    else if (this.dynamicForm.formGroup.valid && !this.existingname) {
      localStorage.setItem("firstName", this.firstName);
      localStorage.setItem("lastName", this.lastName);
      this.route.navigate(['survey']);
    }
  }

  ngOnInit(): void {
    ReactiveFormConfig.set({
      validationMessage:{
        required:"This field is required"
      }
    })
    this.dynamicForm = this.dynamicFormBuilder.formGroup(this.serverData);
  }

}
