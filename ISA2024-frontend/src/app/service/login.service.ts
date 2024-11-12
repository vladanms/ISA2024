import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
	apiHost: string = 'http://localhost:8091/';
  	headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  

   constructor(private http: HttpClient) { }

  login(credentials: string, password: string): Observable<any>{
	  let loginDTO =
	  {
		  credentials: credentials,
		  password: password
	  }
	  
	      return this.http.post<any>(this.apiHost + 'user/userLogin', loginDTO, {headers: this.headers});
	  }
	  
	
}
