package com.example.MyWebsite.repository.InventoryRepository;

import com.example.MyWebsite.entites.InventoryEntity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductIdAndWarehouseId(Long productId, Long warehouseId);

    // JPQL Query: Jo stock quantity threshold se kam ya barabar ho unhe nikalne ke liye
    @Query("SELECT s FROM Stock s WHERE s.quantity <= s.product.lowStockThreshold")
    List<Stock> findLowStockItems();
}