package com.f1soft.graphql.repository;

import com.f1soft.graphql.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findByDate(String date);
    List<Attendance> findByStudentId(Long studentId);
    Attendance findByStudentIdAndDate(Long studentId, String date);
}
