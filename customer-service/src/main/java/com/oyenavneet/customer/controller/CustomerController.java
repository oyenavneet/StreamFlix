package com.oyenavneet.customer.controller;

import com.oyenavneet.customer.dto.CustomerDetails;
import com.oyenavneet.customer.dto.GenreUpdateRequest;
import com.oyenavneet.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDetails> getCustomer(@PathVariable Integer customerId) {
        var customerDetails = this.customerService.getCustomer(customerId);
        return ResponseEntity.ok(customerDetails);
    }

    @PatchMapping("/{customerId}/genre")
    public ResponseEntity<Void> updateGenre(@PathVariable Integer customerId, @RequestBody GenreUpdateRequest request) {
        this.customerService.updateCustomerGenre(customerId, request);
        return ResponseEntity.noContent().build(); // 204
    }
}
