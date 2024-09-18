import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
    {
        "path" : "",
        "title" : "Login Pagee",
        "component" : LoginComponent
    },
    {
        "path" : "home",
        "title" : "Home Page",
        "component" : HomeComponent
    },
    {
        "path":"register",
        "title":"Register Page",
        "component": RegisterComponent
    }
];
