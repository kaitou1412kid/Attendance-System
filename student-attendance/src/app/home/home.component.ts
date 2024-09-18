import { Component, ChangeDetectorRef } from '@angular/core';
import { LoginService } from '../login.service';
import { AttendanceService } from '../attendance.service';
import { Detail } from '../detail';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Student } from '../student.model';
import { attendanceList } from '../attendancelist.model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  userData : Student | undefined;

  username: string = '';

  userId: number | undefined;

  userDetails : Detail[] = [];

  attendaceRecord! : Detail;
  
  today! : Date;
  
  checkInTime = new Date().getTime();

  attendanceList: attendanceList | undefined;

  attendanceForm = new FormGroup({
    title: new FormControl('')
  })

  constructor(private attendanceService: AttendanceService, private cdr: ChangeDetectorRef, private router: Router){
    this.username = localStorage.getItem('username')!;
    let id = localStorage.getItem('id')!;
    this.userId = Number.parseInt(id);
    this.loadAttendance();
    this.attendaceRecord =  new Detail();
  }

  loadAttendance(){
    this.attendanceService.getAllAttendance(this.userId!).subscribe(
      data => {
        this.userDetails = data
        this.cdr.detectChanges();
      }
    );
  }


  submitAttendance(){
    
    this.today = new Date();
    // this.today.getMinutes().toString().length == 1 ? console.log("0"+this.today.getMinutes()) : console.log(this.today.getMinutes());
    const time = (this.today.getHours().toString().length == 1 ? "0"+this.today.getHours(): this.today.getHours()) +":"+(this.today.getMinutes().toString().length == 1 ? "0"+this.today.getMinutes() : this.today.getMinutes());
    const userId = this.userId!;
    if(this.attendanceForm.value.title === "checkIn"){
      this.attendanceService.submitAttendance(time,userId,this.attendanceForm.value.title).subscribe(()=>{
        this.loadAttendance();
      })      
    }else{
      this.attendanceService.submitAttendance(time,userId,this.attendanceForm.value.title!).subscribe(()=>{
        this.loadAttendance();
      });
    }
    
  }

  logout(){
    localStorage.clear();
    this.router.navigateByUrl('');
  }
}
