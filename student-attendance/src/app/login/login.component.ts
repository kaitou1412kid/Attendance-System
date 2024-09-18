import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { Student } from '../student.model';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  userData: Student | undefined;

  loginForm = new FormGroup({
    username : new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })

  constructor(private router: Router, private loginService: LoginService){}

  onLogin(){
    // this.loginService.setUsername(this.loginForm.value.username!);
    this.loginService.loginUser(this.loginForm.value.username!, this.loginForm.value.password!).
    subscribe(data => {
      console.log(data)
      this.userData = data
      if(this.userData != null){
        // this.loginService.setUserData(this.userData);
        localStorage.setItem('username', this.userData?.name!);
        localStorage.setItem('id', this.userData?.id?.toString()!);
        this.router.navigateByUrl("/home")
      }
    });
  }

  registerBtn(){
    this.router.navigateByUrl("/register");
  }
}
