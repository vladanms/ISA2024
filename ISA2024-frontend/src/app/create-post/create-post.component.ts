import { Component, OnInit } from '@angular/core';
import { CreatePostService } from '../service/create-post.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { tap } from 'rxjs/operators'; 

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.css'
})
export class CreatePostComponent{
	
  createPostForm: FormGroup;
  errorMessage: string = '';
  image: File | null = null;
  

  constructor(
    private fb: FormBuilder,
    private createPostService: CreatePostService,
    private router: Router
  	)
  	{
    this.createPostForm = this.fb.group({
	  content: ['', [Validators.required]],
	  image: [null, [Validators.required]],
      location_x: [0, [Validators.required]],
      location_y: [0, [Validators.required]],
    });
  }
  
  
  	onSubmit() {
    if (this.createPostForm.invalid) {
      return;
    	}
    	
    	const formData = new FormData();
   	 	const owner = localStorage.getItem('loggedUser');
    	if(owner)
   		{
    		formData.append('owner', owner);
    	}
    	formData.append('content', this.createPostForm.get('content')?.value);
   		formData.append('location_x', this.createPostForm.get('location_x')?.value.toString());
   		formData.append('location_y', this.createPostForm.get('location_y')?.value.toString());
   		formData.append('image', this.image!);
   		
   		this.createPostService.createPost(formData).pipe(
      tap(response => {
          if (response && response.success) {
            console.log("Post created!");
          } else if (response && response.error) {
            console.log("Error creating post:", response.error);
          }
      })
    ).subscribe(); 
    }
    
      
    get formControls() 
    {
    return this.createPostForm.controls;
  	}
  
  
  
    onFileSelected(event: any): void 
    {
    	const file = event.target.files[0];
    	this.image = file;
    	this.createPostForm.patchValue({
      	image: file
    	});
  	}
  	
  	back()
	{
		this.router.navigate(['/homepage']);
	}
}
