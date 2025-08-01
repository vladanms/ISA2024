import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
const authService = inject(AuthService);
  const router = inject(Router);
  
  if (authService.loggedIn()) 
  {
    return true;
  } 
  else 
  {
    router.navigate(['/login']); 
    return false;
  }
};
