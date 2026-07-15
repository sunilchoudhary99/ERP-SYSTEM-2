package com.example.MyWebsite.entites.FinanceManagement;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "corporate_budgets")
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String category;
    private double allocatedAmount;
    private double currentSpent;
}
