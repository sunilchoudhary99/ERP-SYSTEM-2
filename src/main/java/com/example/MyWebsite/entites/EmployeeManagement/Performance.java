package com.example.MyWebsite.entites.EmployeeManagement;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "performance_tracking")
@Data
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String reviewPeriod;
    private Integer rating; // 1 to 5
    private String feedback;
}