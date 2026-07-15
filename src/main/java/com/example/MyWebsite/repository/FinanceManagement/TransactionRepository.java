package com.example.MyWebsite.repository.FinanceManagement;


import com.example.MyWebsite.entites.FinanceManagement.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}