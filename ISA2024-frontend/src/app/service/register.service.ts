import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDTO } from '../dto/userDTO';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  apiHost: string = 'http://localhost:8091/';
	  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
	
	  constructor(private http: HttpClient) { }
	  
	  register(userDTO: UserDTO): Observable<any> {
		let user = {
		username: userDTO.username,
		password : userDTO.password,
		email : userDTO.email,
		name : userDTO.name,
		surname : userDTO.surname,
		address : userDTO.address,
		city : userDTO.city,
		country : userDTO.country
		};
		
		return this.http.post<any>(this.apiHost + 'user/register', user, {headers: this.headers});
	
	}
}
