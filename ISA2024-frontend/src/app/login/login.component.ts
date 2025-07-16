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
  
  
  	loginf()
  	{
	  	 this.loginService.login(this.credentials, this.password).subscribe(
      (response) => {
		  console.log(response);
        this.router.navigate(['/homepage']);
      },
      (error) => {
		  console.log(`${error.message}`);
        alert('Login failed! Please check your credentials.');
      }
    );
     }
	
	guestLogin()
  	{
	 	this.router.navigate(['/guest-page']);
  	};
  	
  	register()
  	{
		this.router.navigate(['/register']);
	};
  }
