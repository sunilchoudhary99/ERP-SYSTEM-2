package com.example.MyWebsite.service.SalesManagement;




import com.example.MyWebsite.entites.SalesManagement.Customer;
import com.example.MyWebsite.entites.SalesManagement.SalesOrder;
import com.example.MyWebsite.repository.SalesManagement.CustomerRepository;
import com.example.MyWebsite.repository.SalesManagement.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SalesOrderRepository soRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public SalesOrder bookOrder(SalesOrder order) {
        order.setStatus("PENDING_BILLING"); // Base status upon booking
        return soRepository.save(order);
    }

    public List<SalesOrder> getAllOrders() {
        return soRepository.findAll();
    }

    public SalesOrder settleInvoice(Long id) {
        SalesOrder order = soRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales Order Record Missing"));
        order.setStatus("PAID"); // Mark pipeline complete
        return soRepository.save(order);
    }
}