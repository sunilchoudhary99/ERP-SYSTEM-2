package com.example.MyWebsite.repository.InventoryRepository;

import com.example.MyWebsite.entites.InventoryEntity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}