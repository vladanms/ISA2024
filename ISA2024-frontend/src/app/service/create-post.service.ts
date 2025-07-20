import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreatePostService {
 	  apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json',  'Accept': 'application/json' });
	
	  constructor(private http: HttpClient) { }
	  
	  createPost(formData: FormData): Observable<any> {		
		return this.http.post<any>(`${this.apiHost}post/createPost`, formData, {withCredentials: true});
	
	}
}
