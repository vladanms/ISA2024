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
     this.postService.like()
      .pipe(
        tap({
			next : (response)=> 
			{
				if(response && response.success)
				{
				alert(response.success);
				}
			},
			error: () => 
			{
				alert("You must be logged in to do that!")	
			}
        })
      )
      .subscribe();
  }

  comment(): void {
    this.postService.comment()
      .pipe(
        tap({
			next : (response)=> 
			{
				if(response && response.success)
				{
				alert(response.success);
				}
			},
			error: () => 
			{
				alert("You must be logged in to do that!")	
			}
        })
      )
      .subscribe();
  }
}