import { Component } from '@angular/core';
//import { GuestPageService } from '../service/guest-page-service';
import { PostService } from '../service/post-service';
import { postDTO } from '../dto/postDTO';

@Component({
  selector: 'app-guest-page',
  templateUrl: './guest-page.component.html',
  styleUrl: './guest-page.component.css'
})
export class GuestPageComponent {

  posts: postDTO[] = [];

  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts(): void {
    this.postService.getPosts().subscribe({
      next: (data) => this.posts = data,
      error: (err) => console.error('Error loading posts:', err)
    });
  }
}