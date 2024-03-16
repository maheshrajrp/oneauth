package com.assessment.core.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCustomerReq {

    @NotEmpty(message = "Firstname is mandatory")
    private String firstName;

    @NotEmpty(message = "Last Name is mandatory")
    private String lastName;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Phone is mandatory")
    @Pattern(message = "Phone should be of ten digits consisting only of numbers", regexp="(^$|[0-9]{10})")
    private String phone;

    @NotEmpty(message = "Type is mandatory")
    private String type;
}
