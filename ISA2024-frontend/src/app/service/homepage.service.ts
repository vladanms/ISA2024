import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators'; 


@Injectable({
  providedIn: 'root'
})
export class HomepageService {
	apiHost: string = 'http://localhost:8091/';
  	headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });

  constructor(private http: HttpClient) { }
  
  
logout(): Observable<any> {
    const loggedUser = localStorage.getItem('loggedUser');
    
    if (loggedUser) {
        console.log("Logging out user:", loggedUser);

        // Send logout request to backend
        return this.http.post<any>(`${this.apiHost}user/logout`, loggedUser, { headers: this.headers, withCredentials: true  })
            .pipe(
                tap(response => {
                    if (response && response.message) {
                        console.log("Logout successful:", response.message);
                        localStorage.removeItem('loggedUser');
                    } else if (response && response.error) {
                        console.log("Error during logout:", response.error);
                    }
                })
            );
    } else {
        console.log("No user is logged in.");
        return new Observable();
    }
}
}
