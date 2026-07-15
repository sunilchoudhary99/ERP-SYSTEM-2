package com.example.MyWebsite.entites.InventoryEntity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "warehouses")
@Data
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;     // e.g., "Delhi Hub", "Mumbai Warehouse"
    private String location; // e.g., "Okhla Phase 3", "Andheri East"
}