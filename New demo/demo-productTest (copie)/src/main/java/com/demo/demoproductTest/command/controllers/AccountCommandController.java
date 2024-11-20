package com.demo.demoproductTest.command.controllers;


import com.demo.demoproductTest.commandApi.commands.CreateAccoundCommand;
import com.demo.demoproductTest.commandApi.dto.CreateAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController {

    //lorsqu'on envoi une requete on doit executer une commande

    private CommandGateway commandGateway;

    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //creer un compte
    @PostMapping("/create")
    public CompletableFuture<String> createNewAccount(@RequestBody CreateAccountRequestDTO request)
    {
        //on execute une commande et on la publie
       return commandGateway.send(new CreateAccoundCommand(
                UUID.randomUUID().toString(),
                request.getCurreny(),
                request.getInitialBalance()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exptionHandler(Exception exception)
    {
        //on execute une commande et on la publie
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
