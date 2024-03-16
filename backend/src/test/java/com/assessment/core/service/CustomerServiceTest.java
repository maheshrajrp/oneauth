package com.assessment.core.service;

import com.assessment.core.dto.customer.GetAllCustomerRes;
import com.assessment.core.entities.CustomerEntity;
import com.assessment.core.exception.CoreException;
import com.assessment.core.repository.CustomerRepository;
import com.assessment.core.testutils.BaseTestContainerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest extends BaseTestContainerTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
    }


    // Test cases begins
    @Test
    void storedCustomerIsRetrieved() throws Exception {
        CustomerEntity entity = new CustomerEntity("a","Mahesh", "R P", "maheshraj.rp@mock.com", "9876543210", "new");
        customerRepository.save(entity);
        List<GetAllCustomerRes> allCustomers = customerService.getAllCustomers();
        assertEquals(1, allCustomers.size());
    }

    @Test
    void createdCustomerCanBeDeleted() throws Exception {
        CustomerEntity entity = new CustomerEntity(null,"Mahesh", "R P", "maheshraj.rp@mock.com","9876543210","new");
        customerRepository.saveAndFlush(entity);
        customerService.deleteCustomerById(entity.getId());
        Assertions.assertFalse(customerRepository.existsByEmail(entity.getEmail()));
    }

    @Test
    void exceptionIsThrownWhenAccessingInvalidEntry() throws Exception {
       assertThrows(CoreException.class, () -> customerService.getCustomerById("dummy"));
    }

    @Test
    void exceptionIsThrownWhenDeletingAnInvalidEntry() throws Exception {
        assertThrows(CoreException.class, () -> customerService.deleteCustomerById("dummy"));
    }

}
