import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomepageService } from '../service/homepage.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {

  constructor(private homepageService: HomepageService, private router: Router, private toastr: ToastrService) { }
  
  	logout()
  	{
	  	 this.homepageService.logout().subscribe(
      (response) => {
		  console.log(response);
        this.router.navigate(['/login']);
      },
      (error) => {
		  console.log(`${error.message}`);
      }
    );
     }

}
