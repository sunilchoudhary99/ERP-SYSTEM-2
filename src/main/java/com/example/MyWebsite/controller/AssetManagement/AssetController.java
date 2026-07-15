package com.example.MyWebsite.controller.AssetManagement;

import com.example.MyWebsite.entites.AssetManagement.Asset;
import com.example.MyWebsite.service.AssetManagement.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hr/assets")
public class AssetController {

    @Autowired private AssetService assetService;

    @PostMapping("/add")
    public Asset addAsset(@RequestBody Asset asset) {
        return assetService.addNewAsset(asset);
    }

    @PutMapping("/allocate")
    public ResponseEntity<?> allocate(@RequestBody Map<String, Long> payload) {
        try {
            Long assetId = payload.get("assetId");
            Long empId = payload.get("employeeId");
            return ResponseEntity.ok(assetService.allocateAsset(assetId, empId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<?> returnAsset(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(assetService.deallocateAsset(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Asset> getAll() {
        return assetService.getAllAssets();
    }
}