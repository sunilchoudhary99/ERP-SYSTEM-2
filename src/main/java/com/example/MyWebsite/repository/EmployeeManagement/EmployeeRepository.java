package com.example.MyWebsite.repository.EmployeeManagement;




import com.example.MyWebsite.entites.EmployeeManagement.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom query methods
    List<Employee> findByDepartment(String department);
    List<Employee> findByStatus(String status);
}