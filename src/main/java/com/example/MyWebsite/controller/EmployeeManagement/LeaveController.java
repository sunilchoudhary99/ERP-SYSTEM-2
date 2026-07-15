package com.example.MyWebsite.controller.EmployeeManagement;

import com.example.MyWebsite.entites.EmployeeManagement.LeaveRequest;
import com.example.MyWebsite.repository.EmployeeManagement.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hr/leaves")
public class LeaveController {
    @Autowired private LeaveRequestRepository leaveRepository;

    @PostMapping("/request")
    public LeaveRequest requestLeave(@RequestBody LeaveRequest request) {
        request.setStatus("PENDING");
        return leaveRepository.save(request);
    }

    @GetMapping("/pending")
    public List<LeaveRequest> getPending() { return leaveRepository.findByStatus("PENDING"); }

    @PutMapping("/approve/{id}")
    public LeaveRequest approve(@PathVariable Long id, @RequestParam String status) {
        LeaveRequest lr = leaveRepository.findById(id).orElseThrow();
        lr.setStatus(status); // APPROVED or REJECTED
        return leaveRepository.save(lr);
    }
}