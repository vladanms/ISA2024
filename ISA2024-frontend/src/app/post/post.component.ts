import { Component, Input, Output, EventEmitter } from '@angular/core';
import { postDTO } from '../dto/postDTO';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  
  @Input() posts: postDTO[] = [];


  like(id: number): void {
    console.log('You liked the post with the id of:', id);
  }

  comment(id: number): void {
    console.log('You commented on the post with the id of:', id);
  }
}