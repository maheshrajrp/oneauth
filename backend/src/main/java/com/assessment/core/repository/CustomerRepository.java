package com.assessment.core.repository;

import com.assessment.core.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    boolean existsByEmail(String email);
}
