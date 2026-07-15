package com.example.MyWebsite.repository.EmployeeManagement;

import com.example.MyWebsite.entites.EmployeeManagement.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}