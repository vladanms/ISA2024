import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomepageService } from '../service/homepage.service';
import { ToastrService } from 'ngx-toastr';
import { PostService } from '../service/post-service';
import { postDTO } from '../dto/postDTO';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {

posts: postDTO[] = [];

  constructor(private homepageService: HomepageService, private postService: PostService, private router: Router) { }
  
  	ngOnInit(): void {
	if(localStorage.getItem('loggedUser')){
     this.loadPosts();
     }
     else
     this.router.navigate(['/login']);
 	 }
  
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
     
    createPost()
    {
		this.router.navigate(['/createPost']);
	}
	
	 loadPosts(): void {
    this.postService.getPosts().subscribe({
      next: (data) => this.posts = data,
      error: (err) => console.error('Error loading posts:', err)
    });
  }

}
