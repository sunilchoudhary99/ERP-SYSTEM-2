package com.example.MyWebsite.service.PurchaseManagement;





import com.example.MyWebsite.entites.PurchaseManagement.PurchaseOrder;
import com.example.MyWebsite.entites.PurchaseManagement.Vendor;
import com.example.MyWebsite.repository.PurchaseManagement.PurchaseOrderRepository;
import com.example.MyWebsite.repository.PurchaseManagement.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private PurchaseOrderRepository poRepository;

    // Vendor Operations
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    // Purchase Order Initialization
    public PurchaseOrder createPO(PurchaseOrder po) {
        // Initial state set to First Gate review automatically
        po.setStatus("PENDING_MANAGER");
        return poRepository.save(po);
    }

    public List<PurchaseOrder> getAllPOs() {
        return poRepository.findAll();
    }

    // Multi-Stage Workflow Gate Processing Logic
    public PurchaseOrder processApproval(Long id, String action) {
        PurchaseOrder po = poRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order Not Found"));

        if ("REJECT".equalsIgnoreCase(action)) {
            po.setStatus("DISPUTED"); // Automatically flagged on rejection
        } else if ("APPROVE".equalsIgnoreCase(action)) {
            switch (po.getStatus()) {
                case "PENDING_MANAGER":
                    // Promoted to Second Gate review
                    po.setStatus("PENDING_FINANCE");
                    break;
                case "PENDING_FINANCE":
                    // Promoted to Accounting Ledger and triggers Invoice Matching state
                    po.setStatus("AWAITING_PAYMENT");
                    break;
                default:
                    break;
            }
        }
        return poRepository.save(po);
    }

    // Invoice Payout Settlement
    public PurchaseOrder settleInvoice(Long id) {
        PurchaseOrder po = poRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order Not Found"));

        if ("AWAITING_PAYMENT".equals(po.getStatus())) {
            // Funds released, purchase cycle marked complete
            po.setStatus("APPROVED");
        }
        return poRepository.save(po);
    }
}