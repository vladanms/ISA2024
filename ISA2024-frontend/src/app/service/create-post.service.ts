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
	  
	  create_post(content : string, image : File, location_x : number, location_y : number): Observable<any> {
		let postDTO = {

		};
		
		return this.http.post<any>(this.apiHost + 'user/userRegister', postDTO, {headers: this.headers});
	
	}
}
