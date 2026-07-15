package com.example.MyWebsite.repository.SalesManagement;




import com.example.MyWebsite.entites.SalesManagement.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {}