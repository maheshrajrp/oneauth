package com.assessment.core.utils;

import com.assessment.core.dto.customer.CreateCustomerReq;
import com.assessment.core.dto.customer.GetAllCustomerRes;
import com.assessment.core.dto.customer.GetCustomerRes;
import com.assessment.core.entities.CustomerEntity;

public class CustomerUtils {

    public static GetAllCustomerRes getAllCustomerRes(CustomerEntity customerEntity) {
        return GetAllCustomerRes.builder().id(customerEntity.getId()).firstName(customerEntity.getFirstName()).lastName(customerEntity.getLastName()).email(customerEntity.getEmail()).phone(customerEntity.getPhone()).type(customerEntity.getType()).build();
    }

    public static GetCustomerRes getCustomerRes(CustomerEntity customerEntity) {
        return GetCustomerRes.builder().id(customerEntity.getId()).firstName(customerEntity.getFirstName()).lastName(customerEntity.getLastName()).email(customerEntity.getEmail()).phone(customerEntity.getPhone()).type(customerEntity.getType()).build();
    }

    public static CustomerEntity getCustomerEntity(CreateCustomerReq customerReq) {
        return CustomerEntity.builder().firstName(customerReq.getFirstName()).lastName(customerReq.getLastName()).email(customerReq.getEmail()).phone(customerReq.getPhone()).type(customerReq.getType()).build();
    }
}
