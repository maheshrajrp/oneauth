package com.assessment.core.controller;

import com.assessment.core.dto.customer.CreateCustomerReq;
import com.assessment.core.dto.customer.GetAllCustomerRes;
import com.assessment.core.dto.customer.GetCustomerRes;
import com.assessment.core.dto.generic.GenericArrRes;
import com.assessment.core.dto.generic.GenericCreatedRes;
import com.assessment.core.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
@SecurityRequirement(name = "Authentication")

public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Get all customers", description = "List all customers stored in the database")
    @GetMapping()
    public ResponseEntity<GenericArrRes<GetAllCustomerRes>> getAllCustomers() {
        return ResponseEntity.ok(new GenericArrRes<>(customerService.getAllCustomers()));
    }

    @Operation(summary = "Get detailed customer information", description = "Get detailed information about a particular customer")
    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerRes> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Operation(summary = "Create customer", description = "Create an entry for customer by posting information about him")
    @PostMapping()
    public ResponseEntity<GenericCreatedRes> createCustomer(@Valid  @RequestBody CreateCustomerReq req) {
        return new ResponseEntity<>(new GenericCreatedRes(customerService.createCustomer(req)), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete customer", description = "Delete a customer entry by id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Entity Deleted");
    }

}
