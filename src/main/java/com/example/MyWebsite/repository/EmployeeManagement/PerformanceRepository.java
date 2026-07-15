package com.example.MyWebsite.repository.EmployeeManagement;

import com.example.MyWebsite.entites.EmployeeManagement.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}