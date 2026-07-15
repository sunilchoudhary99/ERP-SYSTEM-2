package com.example.MyWebsite.repository.PurchaseManagement;




import com.example.MyWebsite.entites.PurchaseManagement.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}