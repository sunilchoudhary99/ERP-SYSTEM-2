package com.example.MyWebsite.repository.SalesManagement;



import com.example.MyWebsite.entites.SalesManagement.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}