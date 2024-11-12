import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { GuestPageComponent } from './guest-page/guest-page.component';
import { HomepageComponent } from './homepage/homepage.component';
import { CreatePostComponent } from './create-post/create-post.component';

const routes: Routes = [
	{path: 'login', component : LoginComponent},
	{path: 'guest-page', component : GuestPageComponent},
	{path: 'register', component : RegisterComponent},
	{path: 'homepage', component : HomepageComponent},
	{path: 'create-post', component : CreatePostComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
