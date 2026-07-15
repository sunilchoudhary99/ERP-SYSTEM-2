package com.example.MyWebsite.repository.InventoryRepository;

import com.example.MyWebsite.entites.InventoryEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}