import { Component, OnInit } from '@angular/core';
import { CreatePostService } from '../service/create-post.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.css'
})
export class CreatePostComponent implements OnInit {
	
	content : string = '';
	image : File = {} as File;
	location_x : number = 0;
	location_y : number = 0;
	owner : any = '';
	
	 ngOnInit(): void
	 {		
		this.owner = localStorage.getItem('currentUser');
	 }
	
	 constructor(private createPostService: CreatePostService, private router: Router, private toastr: ToastrService) { }
	
	submit(){
		
	/*	if(this.content == "" || this.location_x == null || this.location_y == null || this.image == null)
	   {
		   this.toastr.error("Please fill out all fields", "Error");
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
          this.router.navigate(['/login']);
        },
        error: (e) => 
        {
	     this.toastr.error("Error registering; make sure all required fields are unique", "error");
       }
    	});*/
	};
	
	back(){};
}
