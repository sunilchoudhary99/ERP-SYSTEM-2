package com.example.MyWebsite.entites.FinanceManagement;



import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "general_ledger")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // INCOME, EXPENSE
    private String category;
    private double amount;
    private String description;
    private LocalDate transactionDate;
}