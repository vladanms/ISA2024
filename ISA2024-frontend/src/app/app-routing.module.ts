import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { GuestPageComponent } from './guest-page/guest-page.component';

const routes: Routes = [
	{path: 'login', component : LoginComponent},
	{path: 'guest-page', component : GuestPageComponent},
	{path: 'register', component : RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
