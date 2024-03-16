package com.assessment.core.controller;

import com.assessment.core.dto.customer.CreateCustomerReq;
import com.assessment.core.dto.generic.GenericCreatedRes;
import com.assessment.core.entities.CustomerEntity;
import com.assessment.core.repository.CustomerRepository;
import com.assessment.core.testutils.BaseTestContainerTest;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest extends BaseTestContainerTest {



    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        RestAssured.baseURI = "http://localhost:" + port;
        customerRepository.deleteAll();
    }

    @Test
    void storedCustomerIsRetrieved() throws Exception {
        CustomerEntity entity = new CustomerEntity("a","Mahesh", "R P", "maheshraj.rp@mock.com", "9876543210", "new");
        customerRepository.save(entity);
        ResultActions resultActions = this.mockMvc.perform(get("/v1/customers").with(jwt()));
        resultActions.andExpect(content().string(containsString("maheshraj.rp@mock.com")));
        resultActions.andExpect(content().string(containsString("Mahesh")));
        resultActions.andExpect(content().string(containsString("R P")));
    }


    @Test
    void customerIsCreatedAndStoredInDatabased() throws Exception {
        CreateCustomerReq createCustomerReq = new CreateCustomerReq("Maheshraj", "R P", "maheshraj.rp@mock.com", "9876543210","new");
        String requestJson = objectMapper.writeValueAsString(createCustomerReq);
        MvcResult result = this.mockMvc.perform(post("/v1/customers").contentType(APPLICATION_JSON).content(requestJson).with(jwt())).andExpect(status().isCreated()).andReturn();
        String res = result.getResponse().getContentAsString();
        GenericCreatedRes resObject = objectMapper.readValue(res, GenericCreatedRes.class);
        CustomerEntity customerEntity = customerRepository.findById(resObject.getId()).get();
        Assertions.assertEquals(customerEntity.getEmail(), createCustomerReq.getEmail());
    }

    @Test
    void createdCustomerCanBeDeleted() throws Exception {
        CustomerEntity entity = new CustomerEntity(null,"Mahesh", "R P", "maheshraj.rp@mock.com","9876543210","new");
        customerRepository.saveAndFlush(entity);
        this.mockMvc.perform(delete("/v1/customers/"+entity.getId()).contentType(APPLICATION_JSON).with(jwt())).andExpect(status().isOk());
        Assertions.assertFalse(customerRepository.existsByEmail(entity.getEmail()));
    }

    @Test
    void exceptionIsThrownWhenAccessingInvalidEntry() throws Exception {
        this.mockMvc.perform(get("/v1/customers/abc").with(jwt())).andExpect(status().is(404));
    }

    @Test
    void exceptionIsThrownWhenDeletingAnInvalidEntry() throws Exception {
        this.mockMvc.perform(delete("/v1/customers/abc").with(jwt())).andExpect(status().is(404));
    }


}