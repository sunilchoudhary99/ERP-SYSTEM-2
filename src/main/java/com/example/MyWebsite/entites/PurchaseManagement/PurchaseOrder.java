package com.example.MyWebsite.entites.PurchaseManagement;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "purchase_orders")
@Data
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vendorId;
    private Long productId;
    private int quantity;
    private double totalAmount;

    private String status; // PENDING_MANAGER, PENDING_FINANCE, APPROVED, AWAITING_PAYMENT, DISPUTED
}