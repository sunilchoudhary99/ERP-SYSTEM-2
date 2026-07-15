package com.example.MyWebsite.entites.EmployeeManagement;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data // Lombok: automatically generates getters, setters, toString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String department;
    private String designation;
    private Double basicSalary;

    private LocalDate joiningDate;
    private String status; // Active, OnLeave, Resigned
}