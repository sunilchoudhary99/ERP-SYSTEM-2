package com.example.MyWebsite.controller.SalesManagement;




import com.example.MyWebsite.entites.SalesManagement.Customer;
import com.example.MyWebsite.entites.SalesManagement.SalesOrder;
import com.example.MyWebsite.service.SalesManagement.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesRestController {

    @Autowired
    private SalesService salesService;

    @PostMapping("/customers/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(salesService.saveCustomer(customer));
    }

    @GetMapping("/customers/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(salesService.getAllCustomers());
    }

    @PostMapping("/orders/book")
    public ResponseEntity<SalesOrder> bookSalesOrder(@RequestBody SalesOrder order) {
        return ResponseEntity.ok(salesService.bookOrder(order));
    }

    @GetMapping("/orders/all")
    public ResponseEntity<List<SalesOrder>> getAllOrders() {
        return ResponseEntity.ok(salesService.getAllOrders());
    }

    @PutMapping("/orders/settle/{id}")
    public ResponseEntity<SalesOrder> collectPayment(@PathVariable Long id) {
        return ResponseEntity.ok(salesService.settleInvoice(id));
    }
}