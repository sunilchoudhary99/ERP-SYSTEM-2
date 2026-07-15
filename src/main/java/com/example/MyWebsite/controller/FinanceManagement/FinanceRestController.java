package com.example.MyWebsite.controller.FinanceManagement;



import com.example.MyWebsite.entites.FinanceManagement.Budget;
import com.example.MyWebsite.service.FinanceManagement.FinanceService;
import com.example.MyWebsite.entites.FinanceManagement.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/finance")
public class FinanceRestController {

    @Autowired
    private FinanceService financeService;

    @PostMapping("/transactions/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction tx) {
        boolean budgetBreached = financeService.recordTransaction(tx);
        if (budgetBreached) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(tx); // Status code 409 for Budget Warnings
        }
        return ResponseEntity.ok(tx);
    }

    @GetMapping("/transactions/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(financeService.getAllTransactions());
    }

    @PostMapping("/budgets/set")
    public ResponseEntity<Budget> setBudget(@RequestBody Budget budget) {
        return ResponseEntity.ok(financeService.setBudget(budget));
    }

    @GetMapping("/budgets/all")
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return ResponseEntity.ok(financeService.getAllBudgets());
    }
}