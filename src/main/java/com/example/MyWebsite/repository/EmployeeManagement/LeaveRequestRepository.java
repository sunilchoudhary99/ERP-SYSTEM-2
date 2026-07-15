package com.example.MyWebsite.repository.EmployeeManagement;

import com.example.MyWebsite.entites.EmployeeManagement.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(String status); // Custom query to get pending leaves
}