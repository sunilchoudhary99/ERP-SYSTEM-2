package com.example.MyWebsite.controller.EmployeeManagement;

import com.example.MyWebsite.entites.EmployeeManagement.Attendance;
import com.example.MyWebsite.repository.EmployeeManagement.AttendanceRepository;
import com.example.MyWebsite.repository.EmployeeManagement.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/hr/attendance")
public class AttendanceController {

    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private EmployeeRepository employeeRepository; // <-- Ye add karein

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn(@RequestBody Attendance attendance) {
        // Pehle check karo employee database me hai ya nahi
        Long empId = attendance.getEmployee().getId();
        if (!employeeRepository.existsById(empId)) {
            return ResponseEntity.badRequest().body("Error: Employee ID " + empId + " nahi mila!");
        }

        attendance.setDate(LocalDate.now());
        attendance.setCheckInTime(LocalTime.now());
        attendance.setStatus("PRESENT");

        Attendance saved = attendanceRepository.save(attendance);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/check-out/{id}")
    public Attendance checkOut(@PathVariable Long id) {
        Attendance att = attendanceRepository.findById(id).orElseThrow();
        att.setCheckOutTime(LocalTime.now());
        return attendanceRepository.save(att);
    }
}