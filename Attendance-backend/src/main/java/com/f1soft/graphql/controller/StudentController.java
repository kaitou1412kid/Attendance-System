package com.f1soft.graphql.controller;

import com.f1soft.graphql.model.LoginDTO;
import com.f1soft.graphql.model.ResponseObject;
import com.f1soft.graphql.model.Student;
import com.f1soft.graphql.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginDTO loginDTO) {
        ResponseObject responseObject = studentService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if(responseObject.getStatus() == 200){
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(responseObject, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody LoginDTO loginDTO) {
        ResponseObject responseObject = studentService.save(loginDTO.getUsername(), loginDTO.getPassword());
        if(responseObject.getStatus() == 200){
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(responseObject, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getAttendanceList/{id}")
    public ResponseEntity<ResponseObject> getAttendanceList(@PathVariable("id") String id) {
        Long studentId = Long.parseLong(id);
        ResponseObject responseObject = studentService.getAttendanceList(studentId);
        if(responseObject.getStatus() == 200){
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(responseObject, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/checkIn/{id}")
    public ResponseEntity<ResponseObject> checkIn(@PathVariable("id") String id, @RequestParam String checkIn) {
        Long studentId = Long.parseLong(id);
        ResponseObject responseObject = studentService.setCheckIn(checkIn, studentId);
        if(responseObject.getStatus() == 200){
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(responseObject, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/checkOut/{id}")
    public ResponseEntity<ResponseObject> checkOut(@PathVariable("id") String id, @RequestParam String checkOut) {
        Long studentId = Long.parseLong(id);
        ResponseObject responseObject = studentService.setCheckout(checkOut, studentId);
        if(responseObject.getStatus() == 200){
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(responseObject, HttpStatus.UNAUTHORIZED);
        }
    }
}
