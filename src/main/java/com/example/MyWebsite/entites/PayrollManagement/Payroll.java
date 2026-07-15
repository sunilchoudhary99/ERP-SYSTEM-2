package com.example.MyWebsite.entites.PayrollManagement;

import com.example.MyWebsite.entites.EmployeeManagement.Employee;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "payroll")
@Data
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private String salaryMonth; // e.g., "July 2026"
    private Double allowances;   // Bonus, HRA etc.
    private Double deductions;   // PF, Taxes etc.
    private Double netSalary;    // Calculated: Basic + Allowances - Deductions
    private LocalDate paymentDate;
    private String status;       // PAID, PENDING
}