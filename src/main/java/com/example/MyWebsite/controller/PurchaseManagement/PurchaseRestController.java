package com.example.MyWebsite.controller.PurchaseManagement;





import com.example.MyWebsite.entites.PurchaseManagement.PurchaseOrder;
import com.example.MyWebsite.entites.PurchaseManagement.Vendor;
import com.example.MyWebsite.service.PurchaseManagement.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseRestController {

    @Autowired
    private PurchaseService purchaseService;

    // ------------------------------------------
    // VENDOR SUBSYSTEM ENDPOINTS
    // ------------------------------------------
    @PostMapping("/vendors/add")
    public ResponseEntity<Vendor> addVendor(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(purchaseService.saveVendor(vendor));
    }

    @GetMapping("/vendors/all")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(purchaseService.getAllVendors());
    }

    // ------------------------------------------
    // PURCHASE ORDER & P2P API PIPELINE
    // ------------------------------------------
    @PostMapping("/pos/create")
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder po) {
        return ResponseEntity.ok(purchaseService.createPO(po));
    }

    @GetMapping("/pos/all")
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        return ResponseEntity.ok(purchaseService.getAllPOs());
    }

    @PutMapping("/pos/approve/{id}")
    public ResponseEntity<PurchaseOrder> approvePurchaseOrder(
            @PathVariable Long id,
            @RequestParam String action) {
        return ResponseEntity.ok(purchaseService.processApproval(id, action));
    }

    @PutMapping("/pos/settle/{id}")
    public ResponseEntity<PurchaseOrder> settleInvoicePayment(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.settleInvoice(id));
    }
}