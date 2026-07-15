package com.example.MyWebsite.controller.InventoryController;

import com.example.MyWebsite.entites.InventoryEntity.Product;
import com.example.MyWebsite.entites.InventoryEntity.Stock;
import com.example.MyWebsite.entites.InventoryEntity.Warehouse;
import com.example.MyWebsite.service.InventoryService.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired private InventoryService inventoryService;

    // Products Endpoints
    @PostMapping("/products/add")
    public Product addProduct(@RequestBody Product p) { return inventoryService.saveProduct(p); }

    @GetMapping("/products/all")
    public List<Product> getProducts() { return inventoryService.getAllProducts(); }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        inventoryService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }

    // Warehouse Endpoints
    @PostMapping("/warehouses/add")
    public Warehouse addWarehouse(@RequestBody Warehouse w) { return inventoryService.saveWarehouse(w); }

    @GetMapping("/warehouses/all")
    public List<Warehouse> getWarehouses() { return inventoryService.getAllWarehouses(); }

    // Stock Operations
    @PostMapping("/stock/update")
    public Stock updateStock(@RequestBody Map<String, Object> data) {
        Long pId = Long.parseLong(data.get("productId").toString());
        Long wId = Long.parseLong(data.get("warehouseId").toString());
        Integer qty = Integer.parseInt(data.get("quantity").toString());
        return inventoryService.updateStock(pId, wId, qty);
    }

    @GetMapping("/stock/all")
    public List<Stock> getStock() { return inventoryService.getAllStock(); }

    @GetMapping("/stock/low-alerts")
    public List<Stock> getLowStock() { return inventoryService.getLowStockAlerts(); }

    // Inter-Warehouse Transfer
    @PostMapping("/stock/transfer")
    public ResponseEntity<?> transferStock(@RequestBody Map<String, Object> data) {
        try {
            Long pId = Long.parseLong(data.get("productId").toString());
            Long fromId = Long.parseLong(data.get("fromWarehouseId").toString());
            Long toId = Long.parseLong(data.get("toWarehouseId").toString());
            Integer qty = Integer.parseInt(data.get("quantity").toString());

            inventoryService.transferStock(pId, fromId, toId, qty);
            return ResponseEntity.ok("Stock transfer successfully executed!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}