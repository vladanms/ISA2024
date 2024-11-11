import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from '../service/register.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})


export class RegisterComponent {
	
		username: string = "";
		password : string = "";
		repeat_password: string = "";
		email : string = "";
		name : string = "";
		surname : string = "";
		address : string = "";
		city : string = "";
		country : string = "";

constructor(private registerService: RegisterService, private router: Router, private toastr: ToastrService) { }
 
   submit()
   {
	   if(this.username == "" || this.password == "" || this.email == "" || this.name == "" || this.surname == "" || 
	    this.address == "" || this.city == "" || this.country == "")
	   {
		   this.toastr.error("Please fill out the full form", "Error");
	   }
	   if(this.password != this.repeat_password)
	   {
		   this.toastr.error("Passwords don't match", "Error");
	   }
	   
		this.registerService.register(this.username,this.password,this.email,this.name,this.surname
		,this.address,this.city,this.country).subscribe(
      {
        next: (res) => 
        {
          this.toastr.success("Succesfully registered", "success");
        },
        error: (e) => 
        {
	     this.toastr.error("Error registering; make sure all required fields are unique", "error");
       }
    	});
	   }

	back()
	{
		this.router.navigate(['/login']);
	}

}
