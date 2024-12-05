package com.demo.AccountAxon.repository;

import com.demo.AccountAxon.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
