package com.assessment.core.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAllCustomerRes {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String type;

}
