import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreatePostService {
 
 apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
	
	  constructor(private http: HttpClient) { }
	  
	  create_post(content : string, ): Observable<any> {
		let userDTO = {

		};
		
		return this.http.post<any>(this.apiHost + 'user/userRegister', userDTO, {headers: this.headers});
	
	}
}
