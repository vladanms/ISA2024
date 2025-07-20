import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }
  
  loggedIn(): boolean {
    if(localStorage.getItem('loggedUser'))
    {
		return true;
	}
	else
		return false;
  }
}
