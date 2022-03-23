import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule} from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { YesNoDialogComponent } from './Components/Shared/yes-no-dialog/yes-no-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SurveyComponent } from './survey/survey.component';
import { ResultsComponent } from './results/results.component';
import { RxReactiveFormsModule } from '@rxweb/reactive-form-validators';
import { RxReactiveDynamicFormsModule } from '@rxweb/reactive-dynamic-forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SurveyComponent,
    ResultsComponent,
    YesNoDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatRadioModule,
    FormsModule,
    ReactiveFormsModule,
    RxReactiveFormsModule,
    RxReactiveDynamicFormsModule,
    MatDialogModule,
    MatCardModule,
    BrowserAnimationsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { 
}

