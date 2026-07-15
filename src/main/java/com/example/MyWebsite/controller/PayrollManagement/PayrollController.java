package com.example.MyWebsite.controller.PayrollManagement;

import com.example.MyWebsite.entites.PayrollManagement.Payroll;
import com.example.MyWebsite.service.PayrollManagement.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hr/payroll")
public class PayrollController {

    @Autowired private PayrollService payrollService;

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody Map<String, Object> data) {
        try {
            Long empId = Long.parseLong(data.get("employeeId").toString());
            String month = data.get("month").toString();
            Double allowances = Double.parseDouble(data.get("allowances").toString());
            Double deductions = Double.parseDouble(data.get("deductions").toString());

            Payroll payroll = payrollService.generatePayroll(empId, month, allowances, deductions);
            return ResponseEntity.ok(payroll);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<?> paySalary(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(payrollService.markAsPaid(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Payroll> getAll() {
        return payrollService.getAllPayroll();
    }
}