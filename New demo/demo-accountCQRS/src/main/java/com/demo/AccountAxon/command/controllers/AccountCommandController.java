package com.demo.AccountAxon.command.controllers;


import com.demo.AccountAxon.commandApi.commands.CreateAccoundCommand;
import com.demo.AccountAxon.commandApi.commands.CreditAccoundCommand;
import com.demo.AccountAxon.commandApi.commands.DebitAccoundCommand;
import com.demo.AccountAxon.commandApi.dto.CreateAccountRequestDTO;
import com.demo.AccountAxon.commandApi.dto.CreditAccountRequestDTO;
import com.demo.AccountAxon.commandApi.dto.DebitAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController {

    //lorsqu'on envoi une requete on doit executer une commande

    private CommandGateway commandGateway;
    @Autowired
    private EventStore eventStore;

    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //creer un compte
    @PostMapping("/create")
    public CompletableFuture<String> createNewAccount(@RequestBody CreateAccountRequestDTO request)
    {
        //on execute une commande et on la publie vers l'aggrega pour la traite
       return commandGateway.send(new CreateAccoundCommand(
                UUID.randomUUID().toString(),
                request.getCurreny(),
                request.getInitialBalance()
        ));
    }

    //debiter un compte
    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request)
    {
        //on execute une commande et on la publie vers l'aggrega pour la traite
        return commandGateway.send(new DebitAccoundCommand(
                request.getAacountId(),
                request.getCurreny(),
                request.getAmount()
        ));
    }

    //vrediter un compte
    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request)
    {
        //on execute une commande et on la publie vers l'aggrega pour la traite
        return commandGateway.send(new CreditAccoundCommand(
                request.getAacountId(),
                request.getCurreny(),
                request.getAmount()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exptionHandler(Exception exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //pour lire un aggrega contenu dans l'eventStore
    @GetMapping("/eventStore/{id}")
    public Stream eventStore(@PathVariable String id)
    {
       return eventStore.readEvents(id).asStream();
    }


}
