import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { postDTO } from '../dto/postDTO';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GuestPageService {
	apiHost: string = 'http://localhost:8091/';
  	headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  

  constructor(private http: HttpClient) {}

  getPosts(): Observable<postDTO[]> {
    return this.http.get<postDTO[]>(`${this.apiHost}post/getAllPosts`);
  }
  
}