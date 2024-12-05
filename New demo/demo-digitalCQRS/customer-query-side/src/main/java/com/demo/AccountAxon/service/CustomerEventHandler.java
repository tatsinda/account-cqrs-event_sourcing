package com.demo.AccountAxon.service;

import com.demo.AccountAxon.entity.Customer;
import com.demo.AccountAxon.repository.CustomerRepository;
import com.demo.events.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerEventHandler {

    @Autowired
    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){

        log.info("********* CustomerCreatedEvent received ********");

        Customer customer = new Customer();
        customer.setId(event.getId());
        customer.setName(event.getName());
        customer.setEmail(event.getEmail());
        customerRepository.save(customer);

    }



}
