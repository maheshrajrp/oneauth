package com.assessment.core.service;

import com.assessment.core.dto.customer.CreateCustomerReq;
import com.assessment.core.dto.customer.GetAllCustomerRes;
import com.assessment.core.dto.customer.GetCustomerRes;
import com.assessment.core.entities.CustomerEntity;
import com.assessment.core.exception.CoreError;
import com.assessment.core.exception.CoreException;
import com.assessment.core.repository.CustomerRepository;
import com.assessment.core.utils.CustomerUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {

    final private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<GetAllCustomerRes> getAllCustomers() {
        log.info("Fetching all customer information.");
        return this.customerRepository.findAll().stream().map(CustomerUtils::getAllCustomerRes).toList();
    }

    public GetCustomerRes getCustomerById(String id) {
        log.info("Fetching customer information by id - "+id);
        CustomerEntity customerEntity = this.customerRepository.findById(id).orElseThrow(
                () -> new CoreException(CoreError.USER_NOT_EXISTS)
        );
        return CustomerUtils.getCustomerRes(customerEntity);
    }

    public String createCustomer(CreateCustomerReq newCustomerReq) {
        log.info("Creating customer - " + newCustomerReq);
        CustomerEntity entity = CustomerUtils.getCustomerEntity(newCustomerReq);
        checkAndThrowErrorIfCustomerExists(newCustomerReq.getEmail());
        customerRepository.save(entity);
        log.info("Created customer with payload " + newCustomerReq);
        return entity.getId();
    }

    @Transactional
    public void deleteCustomerById(String id) {
        log.info("Deleting customer by " + id);
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else {
            throw new CoreException(CoreError.USER_NOT_EXISTS);
        }
    }

    public void checkAndThrowErrorIfCustomerExists(String email) {
        if(customerRepository.existsByEmail(email)) {
            throw new CoreException(CoreError.USER_EMAIL_EXISTS);
        }
    }

}
