import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { postDTO } from '../dto/postDTO';

@Injectable({
  providedIn: 'root'
})
export class PostService {
	apiHost: string = 'http://localhost:8091/';
  	headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  

  constructor(private http: HttpClient) {}

  getPosts(): Observable<postDTO[]> {
    return this.http.get<postDTO[]>(`${this.apiHost}post/getAllPosts`, {withCredentials: true});
  }
  
   like(): Observable<any> {
   return this.http.post<any>(`${this.apiHost}post/like`, {}, {withCredentials: true});
  }
  
   comment(): Observable<any> {
   return this.http.post<any>(`${this.apiHost}post/comment`, {}, {withCredentials: true});
  }

  
}