package com.example.MyWebsite.entites.AssetManagement;

import com.example.MyWebsite.entites.EmployeeManagement.Employee;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "assets")
@Data
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetName;      // e.g., "MacBook Pro M3", "Dell Monitor"
    private String assetType;      // e.g., "Electronics", "Furniture"
    private String serialNumber;   // Unique code like "MAC123456"

    @ManyToOne
    @JoinColumn(name = "assigned_employee_id", nullable = true)
    private Employee assignedEmployee; // Kis bande ke paas hai

    private LocalDate assignmentDate;
    private String status;         // AVAILABLE, ASSIGNED, UNDER_REPAIR, DAMAGED
}