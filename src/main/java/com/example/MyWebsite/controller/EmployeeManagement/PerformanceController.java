package com.example.MyWebsite.controller.EmployeeManagement;

import com.example.MyWebsite.entites.EmployeeManagement.Performance;
import com.example.MyWebsite.repository.EmployeeManagement.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hr/performance")
public class PerformanceController {
    @Autowired private PerformanceRepository performanceRepository;

    @PostMapping("/add-review")
    public Performance addReview(@RequestBody Performance performance) {
        return performanceRepository.save(performance);
    }
}