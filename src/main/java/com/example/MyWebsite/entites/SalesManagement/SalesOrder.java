package com.example.MyWebsite.entites.SalesManagement;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sales_orders")
@Data
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long productId;
    private int quantity;
    private double dealPrice;
    private String status; // PENDING_BILLING, PAID, CANCELLED
}