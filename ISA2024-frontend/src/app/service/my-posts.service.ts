import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { postDTO } from '../dto/postDTO';

@Injectable({
  providedIn: 'root'
})
export class MyPostsService implements OnInit{

   apiHost: string = 'http://localhost:8091/';
    headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  
  owner : any = '';
  
  	 ngOnInit(): void
	 {		
		this.owner = localStorage.getItem('currentUser');
	 }

  constructor(private http: HttpClient) { }
  
  getMyPosts(): Observable<postDTO[]>{
	   return this.http.get<postDTO[]>(this.apiHost + 'posts/getPostsByUser?username=' + this.owner , {headers: this.headers});
  }
}
