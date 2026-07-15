package com.example.MyWebsite.entites.InventoryEntity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String sku; // Unique Stock Keeping Unit (e.g., PROD-LOG-01)
    private Double price;
    private String category;
    private Integer lowStockThreshold; // Isse kam stock hone par alert aayega
}