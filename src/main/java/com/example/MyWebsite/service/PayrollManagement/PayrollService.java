package com.example.MyWebsite.service.PayrollManagement;

import com.example.MyWebsite.entites.EmployeeManagement.Employee;
import com.example.MyWebsite.entites.PayrollManagement.Payroll;
import com.example.MyWebsite.repository.EmployeeManagement.EmployeeRepository;
import com.example.MyWebsite.repository.PayrollManagement.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    @Autowired private PayrollRepository payrollRepository;
    @Autowired private EmployeeRepository employeeRepository;

    public Payroll generatePayroll(Long employeeId, String month, Double allowances, Double deductions) {
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));

        Payroll payroll = new Payroll();
        payroll.setEmployee(emp);
        payroll.setSalaryMonth(month);
        payroll.setAllowances(allowances);
        payroll.setDeductions(deductions);

        // Automatic Calculation
        Double netSalary = emp.getBasicSalary() + allowances - deductions;
        payroll.setNetSalary(netSalary);

        payroll.setStatus("PENDING");
        return payrollRepository.save(payroll);
    }

    public Payroll markAsPaid(Long payrollId) {
        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new RuntimeException("Payroll record not found!"));
        payroll.setStatus("PAID");
        payroll.setPaymentDate(LocalDate.now());
        return payrollRepository.save(payroll);
    }

    public List<Payroll> getAllPayroll() {
        return payrollRepository.findAll();
    }
}