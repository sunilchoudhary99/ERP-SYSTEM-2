package com.example.MyWebsite.service.FinanceManagement;


import com.example.MyWebsite.entites.FinanceManagement.Budget;
import com.example.MyWebsite.entites.FinanceManagement.Transaction;
import com.example.MyWebsite.repository.FinanceManagement.BudgetRepository;
import com.example.MyWebsite.repository.FinanceManagement.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceService {

    @Autowired
    private TransactionRepository txRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    public boolean recordTransaction(Transaction tx) {
        boolean overBudgetFlag = false;

        if (tx.getType().equalsIgnoreCase("EXPENSE")) {
            Optional<Budget> budgetOpt = budgetRepository.findByCategoryIgnoreCase(tx.getCategory());
            if (budgetOpt.isPresent()) {
                Budget budget = budgetOpt.get();
                double updatedExpense = budget.getCurrentSpent() + tx.getAmount();
                budget.setCurrentSpent(updatedExpense);
                budgetRepository.save(budget);

                if (updatedExpense > budget.getAllocatedAmount()) {
                    overBudgetFlag = true; // Budget validation check failed
                }
            }
        }
        txRepository.save(tx);
        return overBudgetFlag;
    }

    public List<Transaction> getAllTransactions() {
        return txRepository.findAll();
    }

    public Budget setBudget(Budget budget) {
        Optional<Budget> existing = budgetRepository.findByCategoryIgnoreCase(budget.getCategory());
        if(existing.isPresent()) {
            Budget oldBudget = existing.get();
            oldBudget.setAllocatedAmount(budget.getAllocatedAmount());
            return budgetRepository.save(oldBudget);
        }
        budget.setCurrentSpent(0.0);
        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
}