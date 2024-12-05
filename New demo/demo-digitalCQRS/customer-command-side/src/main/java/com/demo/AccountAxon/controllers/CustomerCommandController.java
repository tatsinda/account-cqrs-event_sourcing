package com.demo.AccountAxon.controllers;

import com.demo.commands.CreateCustomerCommand;
import com.demo.dtos.CustomerRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/customer/commands")
public class CustomerCommandController {
    @Autowired
    private CommandGateway commandGateway;


    @PostMapping("/create")
    public CompletableFuture<String> newCustomer(@RequestBody CustomerRequestDTO requestDTO)
    {
        return commandGateway.send(
                new CreateCustomerCommand(
                        UUID.randomUUID().toString(),
                        requestDTO.getName(),
                        requestDTO.getEmail()
                )
        );
    }

}
