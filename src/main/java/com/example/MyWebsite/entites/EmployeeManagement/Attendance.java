package com.example.MyWebsite.entites.EmployeeManagement;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance")
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String status; // PRESENT, ABSENT, LATE
}