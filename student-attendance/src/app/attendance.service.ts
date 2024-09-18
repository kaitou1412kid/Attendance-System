import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Response } from './response.model';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  baseURL = "http://localhost:8080/";
  
  constructor(private http: HttpClient) { }

  getAllAttendance(id: number):Observable<any>{
    return this.http.get<Response<any>>(this.baseURL+"getAttendanceList/"+id).pipe(map(response => response.data));
  }

  submitAttendance(time: string, id: number, title: string): Observable<any>{
    const options = {params: new HttpParams().set(`${title}`, time)}
    return this.http.get(this.baseURL + `${title}/`+id, options);
  }
}
