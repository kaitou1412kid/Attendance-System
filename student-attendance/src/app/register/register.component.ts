import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Validators } from '@angular/forms';
import { RegisterService } from '../register.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })

  constructor(private registerService: RegisterService, private router: Router){}

  onRegister(username: string, password: string){
    this.registerService.register(username, password).subscribe(data => {
      if(data){
        this.router.navigateByUrl("");
      }else{
        console.log(data);
      }
    })
  }

  loginBtn(){
    this.router.navigateByUrl("");
  }
}
