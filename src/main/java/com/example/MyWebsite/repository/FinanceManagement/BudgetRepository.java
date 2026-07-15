package com.example.MyWebsite.repository.FinanceManagement;


import com.example.MyWebsite.entites.FinanceManagement.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByCategoryIgnoreCase(String category);
}