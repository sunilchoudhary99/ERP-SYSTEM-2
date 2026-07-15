package com.example.MyWebsite.repository.AssetManagement;

import com.example.MyWebsite.entites.AssetManagement.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByStatus(String status);
}