package com.example.MyWebsite.repository.PayrollManagement;

import com.example.MyWebsite.entites.PayrollManagement.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByEmployeeId(Long employeeId);
}