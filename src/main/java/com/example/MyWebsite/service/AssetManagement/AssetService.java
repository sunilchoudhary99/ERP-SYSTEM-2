package com.example.MyWebsite.service.AssetManagement;

import com.example.MyWebsite.entites.AssetManagement.Asset;
import com.example.MyWebsite.entites.EmployeeManagement.Employee;
import com.example.MyWebsite.repository.AssetManagement.AssetRepository;
import com.example.MyWebsite.repository.EmployeeManagement.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AssetService {

    @Autowired private AssetRepository assetRepository;
    @Autowired private EmployeeRepository employeeRepository;

    public Asset addNewAsset(Asset asset) {
        asset.setStatus("AVAILABLE");
        return assetRepository.save(asset);
    }

    public Asset allocateAsset(Long assetId, Long employeeId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found!"));
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));

        asset.setAssignedEmployee(emp);
        asset.setAssignmentDate(LocalDate.now());
        asset.setStatus("ASSIGNED");
        return assetRepository.save(asset);
    }

    public Asset deallocateAsset(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new RuntimeException("Asset not found!"));

        asset.setAssignedEmployee(null);
        asset.setAssignmentDate(null);
        asset.setStatus("AVAILABLE");
        return assetRepository.save(asset);
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
}