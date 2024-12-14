import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  credentials: string = "";
  password: string = "";
  
    ngOnInit(): void {		
		localStorage.setItem('currentUser', '');
	 }

  
  constructor(private loginService: LoginService, private router: Router, private toastr: ToastrService) { }
  
  
  /*	loginf()
  	{
	  	 this.loginService.login(this.credentials, this.password).subscribe(
       	 {
         	 next: (res) => {
         	 localStorage.setItem('currentUser', this.credentials);
         	 console.log("logged");
         	 
         	 this.router.navigate(['/homepage']);
         	 },
         	 error: () => {
				  console.log("failed");
				  this.toastr.error("Invalid credentials", "Error");
          }
      });
     }*/
	
	guestLogin()
  	{
	 	this.router.navigate(['/guest-page']);
  	};
  	
  	register()
  	{
		this.router.navigate(['/register']);
	};
  }
