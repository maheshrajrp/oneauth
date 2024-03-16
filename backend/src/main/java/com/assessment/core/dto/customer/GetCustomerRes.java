package com.assessment.core.dto.customer;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetCustomerRes {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String type;

}
