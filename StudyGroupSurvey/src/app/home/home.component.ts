import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { YesNoDialogComponent } from '../Components/Shared/yes-no-dialog/yes-no-dialog.component';
import { SurveyService } from '../survey.service';
import { LocalSurveyService } from '../localsurvey.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [ SurveyService ]
})
export class HomeComponent implements OnInit {
  
  constructor(private route:Router, public dialog: MatDialog, private surveyService: SurveyService, private localSurveyService: LocalSurveyService) {}
  firstname: string = "";
  lastname: string = "";
  validparameters: boolean = true;
  existingname: boolean = true;

  async startSurvey() {
    //this.checkName(this.firstname, this.lastname);
    if (this.firstname == "" || this.lastname == "") {
      this.validparameters = false;
    }
    else {
      this.validparameters = true;
      await this.surveyService.getSurveyExistance(this.firstname, this.lastname)
        .then(value => {this.existingname = value
        console.log("done")})
        .catch(error => console.log("Much error"))
    }

    if (this.existingname && this.validparameters) {
      const dialogRef = this.dialog.open(YesNoDialogComponent, {
      maxWidth: "400px",
      data: {
        title: "Survey Already Completed",
        message: "Do you want to see your results?"}
      });
      // listen to response
      dialogRef.afterClosed().subscribe(dialogResult => { 
        if (dialogResult) {
          this.localSurveyService.updateNames(this.firstname, this.lastname);
          this.route.navigate(['results']);
        }
      })
    }
    else if (this.validparameters && !this.existingname) {
      this.localSurveyService.updateNames(this.firstname, this.lastname);
      this.route.navigate(['survey']);
    }
    console.log(this.existingname);
  }

  checkName(firstname: string, lastname: string) {
    // this.surveyService.getSurveyExistance(firstname, lastname).subscribe( {
    //   next: (v) => this.existingname = v,
    //   error: (e) => alert(e.message),
    //   }
    // );
  }

  quickTest() {
    this.surveyService.getSurveys().subscribe( {
      error: (e) => alert(e.message)
    })
  }

  ngOnInit(): void {
  }

}
