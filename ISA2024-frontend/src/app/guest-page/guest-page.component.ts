import { Component } from '@angular/core';
import { GuestPageService } from '../service/guest-page-service';
import { postDTO } from '../dto/postDTO';

@Component({
  selector: 'app-guest-page',
  templateUrl: './guest-page.component.html',
  styleUrl: './guest-page.component.css'
})
export class GuestPageComponent {

  posts: postDTO[] = [];

  constructor(private guestPageService: GuestPageService) {}

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts(): void {
    this.guestPageService.getPosts().subscribe({
      next: (data) => this.posts = data,
      error: (err) => console.error('Error loading posts:', err)
    });
  }

  like(id: number): void {
	  console.log('you liked the post with the id of: ', id);
  }

  comment(id: number): void {
	  console.log('you commented on the post with the id of: ', id);
  }
}