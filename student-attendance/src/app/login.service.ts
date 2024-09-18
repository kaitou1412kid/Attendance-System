import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Student } from './student.model';
import { Response } from './response.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // username: string = "";
  baseURL = "http://localhost:8080/";
  userData: Student | undefined;

  
  constructor(private http: HttpClient) { }

  loginUser(username: string, password: string): Observable<any>{
    return this.http.post<Response<any>>(this.baseURL+"login", 
      {
        "username" : username,
        "password": password
      }
    ).pipe(map(response => response.data))
    // return this.userData;
  }

  // setUserData(userData: Object){

  //   localStorage.setItem('username', this.userData?.name!)
  //   this.userData = userData;
  // }

  getUserData(){
    return this.userData;
  }
  
}
