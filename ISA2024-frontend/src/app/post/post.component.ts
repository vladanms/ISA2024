import { Component, Input, Output, EventEmitter } from '@angular/core';
import { PostService } from '../service/post-service';
import { postDTO } from '../dto/postDTO';
import { tap } from 'rxjs';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  
  @Input() posts: postDTO[] = [];

	constructor(private postService: PostService) { }

  like(): void {
     this.postService.like().subscribe(
			(response)=> 
			{
				if(response && response.success)
				{
				alert(response.success);
				}
			},
			(error) => 
			{
				if(error.status === 403) 
				{
        		alert('You must be logged in to do that!');
        		}
        		else
        		{
				console.log(`${error.message}`);
            	alert(error?.error?.error);
            	}
			}
      );
  }

  comment(): void {
    this.postService.comment().subscribe(
			(response)=> 
			{
				if(response && response.success)
				{
				alert(response.success);
				}
			},
			(error) => 
			{
				if(error.status === 403) 
				{
        		alert('You must be logged in to do that!');
        		}
        		else
        		{
				console.log(`${error.message}`);
            	alert(error?.error?.error);
            	}
			}
      );
  }
}