package com.demo.demoUserTest.controller;

import com.demo.demoUserTest.axonConnect.command.CreateUserCommand;
import com.demo.demoUserTest.model.User;
import com.demo.demoUserTest.service.UserServiceImpl;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserCommandController { //controlleur des commandes
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CommandGateway commandGateway; //injection de la gateway axon


    //*****************************Axon server************************//
    @PostMapping(value = "/api/user/create")
    public CompletableFuture<?> createOtherUser(@RequestBody User u)
    {
        CreateUserCommand command = new CreateUserCommand();
        command.setId(UUID.randomUUID().toString());
        command.setNom(u.getNom());
        command.setPrenom(u.getPrenom());
        command.setProducts(u.getProducts());

        return  commandGateway.send(command); //envoi de la commande a la fonction de decision: @CommandHandler a travers le command bus
    }


}
