import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators'; 

@Injectable({
  providedIn: 'root'
})
export class LoginService {
	apiHost: string = 'http://localhost:8091/';
  	headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  

   constructor(private http: HttpClient) { }



 	login(credentials: string, password: string): Observable<any>{
		 console.log("started");
	  let loginDTO =
	  {
		  credentials: credentials,
		  password: password
	  }
	  
	  
	   return this.http.post<any>(`${this.apiHost}user/login`, loginDTO, { headers: this.headers, withCredentials : true })
      .pipe(
		  tap(response => {
          if (response && response.credentials) {
            localStorage.setItem('loggedUser', response.credentials);
            console.log("Username stored in localStorage:", response.credentials);
          } else if (response && response.error) {
            console.log("Error during login:", response.error);
          }
        })
      );
  }
  
}