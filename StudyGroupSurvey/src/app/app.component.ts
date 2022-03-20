import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormArray, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {
  isSubmitted = false;

  productFormGroup!: FormGroup;
  constructor(public formBuilder: FormBuilder) {
  }
  /*########### Template Driven Form ###########*/
  submitForms(forms: NgForm[]) {
    let areFormsGood: Boolean = true;
    for (let i = 0; i < forms.length; i++) {
      areFormsGood = areFormsGood && this.submitForm(forms[i]);
    }
    if (areFormsGood) {
      alert("Horray!");
    }
  }

  submitForm(form: NgForm) {
    this.isSubmitted = true;
    if(!form.valid) {
      return false;
    } 
    //alert(JSON.stringify(form.value))
    return true;
  }

  // productList = [
  //   {value: "q1"},
  //   {value: "q2"},
  //   {value: "q3"}
  // ];


  // ngOnInit() {
  //   this.productFormGroup = this.formBuilder.group({
  //     productItem: ['', Validators.required],
  //   });
  //   //const productsMethodsControl = <FormArray>this.productFormGroup.controls['productMethods'];
  //   // creating radio button control for each item.
  //   //for (let i = 0, length = this.productList.length; i < length; i++) {
  //   //  productsMethodsControl.push(new FormControl(false));
  //   //}
  // }

  // onSubmit() {
  //   console.log(this.productFormGroup);
  // }
}
