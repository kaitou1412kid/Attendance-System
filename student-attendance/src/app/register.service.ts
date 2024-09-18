import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  baseURL = "http://localhost:8080/"

  constructor(private http: HttpClient) { }

  register(username: string, password: string): Observable<any>{
    return this.http.post(this.baseURL + "register", 
      {
        "username": username,
        "password": password
      }
    )
  }
}
