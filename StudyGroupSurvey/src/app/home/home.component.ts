import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { YesNoDialogComponent } from '../Components/Shared/yes-no-dialog/yes-no-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private route:Router, public dialog: MatDialog) {}
  firstname: string = "";
  lastname: string = "";
  validparameters: boolean = true;
  existingname: boolean = false;

  startSurvey() {
    if (this.firstname == "" || this.lastname == "") {
      this.validparameters = false;
    }
    else {
      this.validparameters = true;
    }

    this.existingname = this.checkName(this.firstname, this.lastname);

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
          this.route.navigate(['results']);
        }
      })
      
    }

    if (this.validparameters && !this.existingname) {
      this.route.navigate(['survey']);
    }

  }

  checkName(firstname: string, lastname: string) {
    if (firstname == "Liam" && lastname == "Sparling") {
      return true;
    }
    else {
      return false;
    }
  }

  ngOnInit(): void {
  }

}
