package com.f1soft.graphql.service;

import com.f1soft.graphql.model.Attendance;
import com.f1soft.graphql.model.ResponseObject;
import com.f1soft.graphql.model.Student;
import com.f1soft.graphql.repository.AttendanceRepository;
import com.f1soft.graphql.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    public StudentService(StudentRepository studentRepository, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public ResponseObject login(String username, String password) {
        ResponseObject responseObject = new ResponseObject();
        Student std = studentRepository.findByName(username);
        if (std != null) {
            if (std.getPassword().equals(password)) {
                responseObject.setStatus(200);
                responseObject.setMessage("Successfully Logged In.");
                responseObject.setData(std);
                return responseObject;
            }
        }
        responseObject.setStatus(400);
        responseObject.setMessage("Invalid Credentials");
        responseObject.setData(null);
        return responseObject;
    }

    public ResponseObject getAttendanceList(Long id) {
        Optional<Student> std =  studentRepository.findById(id);
        ResponseObject responseObject = new ResponseObject();

        if(std.isPresent()) {
            Student student = std.get();
            responseObject.setStatus(200);
            responseObject.setMessage("Successfully Get Attendance List.");
            responseObject.setData(student.getAttendanceList());
            return responseObject;
        }
        responseObject.setStatus(400);
        responseObject.setMessage("Invalid Credentials");
        responseObject.setData(null);
        return responseObject;
    }

    public ResponseObject save(String username, String password) {
        Student std = studentRepository.findByName(username);
        ResponseObject responseObject = new ResponseObject();
        if(std == null){
            std = new Student();
            std.setName(username);
            std.setPassword(password);
            studentRepository.save(std);
            responseObject.setStatus(200);
            responseObject.setMessage("Successfully created.");
            responseObject.setData(std);
            return responseObject;
        }
        responseObject.setStatus(400);
        responseObject.setMessage("Student already exists.");
        responseObject.setData(null);
        return responseObject;
    }

    public ResponseObject setCheckIn(String checkIn, Long studentId) {
        Optional<Student> std = studentRepository.findById(studentId);
        ResponseObject responseObject = new ResponseObject();
        Map<String,String> timeData = new HashMap<>();
        if (std.isPresent()) {
            Student student = std.get();
            Attendance attendance = attendanceRepository.findByStudentIdAndDate(studentId,LocalDate.now().toString());
            if(attendance != null) {
                responseObject.setStatus(200);
                responseObject.setMessage("Already checkedIn.");
                timeData.put("checkIn", attendance.getCheckIn());
                responseObject.setData(timeData);
                return responseObject;
            }else{
                attendance = new Attendance();
                attendance.setStudent(student);
                attendance.setDate(LocalDate.now().toString());
                attendance.setCheckIn(checkIn);
                attendanceRepository.save(attendance);
                responseObject.setStatus(200);
                responseObject.setMessage("CheckIn Time successfully updated.");
                timeData.put("checkIn", checkIn);
                responseObject.setData(timeData);
                return responseObject;
            }
        }
        responseObject.setStatus(400);
        responseObject.setMessage("Student does not exist.");
        responseObject.setData(null);
        return responseObject;
    }

//    public ResponseObject setCheckIn(String checkIn, Long studentId) {
//        List<Attendance> attendanceList = attendanceRepository.findByStudentId(studentId);
//        Student student = studentRepository.findById(studentId).get();
//        ResponseObject responseObject = new ResponseObject();
//        Map<String,String> timeData = new HashMap<>();
//        if(attendanceList != null) {
//            for(Attendance attendance : attendanceList) {
//                if(attendance.getCheckIn().equals(checkIn)) {
//                    responseObject.setStatus(200);
//                    responseObject.setMessage("Already checkedIn.");
//                    timeData.put("checkIn", attendance.getCheckIn());
//                    responseObject.setData(timeData);
//                    return responseObject;
//                }
//            }
//            Attendance attendance = new Attendance();
//            attendance.setStudent(student);
//            attendance.setDate(LocalDate.now().toString());
//            attendance.setCheckIn(checkIn);
//            attendanceRepository.save(attendance);
//            responseObject.setStatus(200);
//            responseObject.setMessage("CheckIn Time successfully updated.");
//            timeData.put("checkIn", checkIn);
//            responseObject.setData(timeData);
//            return responseObject;
//        }else{
//            responseObject.setStatus(400);
//            responseObject.setMessage("Student does not exist.");
//            responseObject.setData(null);
//            return responseObject;
//        }
//    }

    public ResponseObject setCheckout(String checkOut, Long studentId){
        Optional<Student> std = studentRepository.findById(studentId);
        ResponseObject responseObject = new ResponseObject();
        Map<String,String> timeData = new HashMap<>();
        if (std.isPresent()) {
            Student student = std.get();
            Attendance attendance = attendanceRepository.findByStudentIdAndDate(studentId,LocalDate.now().toString());
            if(attendance != null) {
                if(attendance.getCheckOut() != null){
                    responseObject.setStatus(200);
                    responseObject.setMessage("CheckOut Time is already set.");
                    timeData.put("checkOut", attendance.getCheckOut());
                    responseObject.setData(timeData);
                    return responseObject;
                }else{
                    attendance.setCheckOut(checkOut);
                    attendanceRepository.save(attendance);
                    responseObject.setStatus(200);
                    responseObject.setMessage("CheckOut Time successfully updated.");
                    timeData.put("checkOut", checkOut);
                    responseObject.setData(timeData);
                    return responseObject;
                }
            }else{
                responseObject.setStatus(400);
                responseObject.setMessage("Please checkin first.");
                responseObject.setData(null);
                return responseObject;
            }

        }
        responseObject.setStatus(400);
        responseObject.setMessage("Student does not exist.");
        responseObject.setData(null);
        return responseObject;
    }
}
