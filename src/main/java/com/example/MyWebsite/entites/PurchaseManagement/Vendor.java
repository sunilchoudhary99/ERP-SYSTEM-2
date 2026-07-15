package com.example.MyWebsite.entites.PurchaseManagement;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vendors")
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private int complianceScore;
    private String status; // Active, Preferred, Onboarding In Progress
}