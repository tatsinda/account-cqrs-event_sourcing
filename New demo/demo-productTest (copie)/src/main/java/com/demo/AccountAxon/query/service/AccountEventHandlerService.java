package com.demo.AccountAxon.query.service;

import com.demo.AccountAxon.commandApi.events.AccountCreatedEvent;
import com.demo.AccountAxon.commandApi.events.AccountCreditedEvent;
import com.demo.AccountAxon.query.entity.Account;
import com.demo.AccountAxon.query.queries.GetAllAccounts;
import com.demo.AccountAxon.query.repository.AccountRepository;
import com.demo.AccountAxon.query.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//creation de la classe service account
@Service
@Transactional
@Slf4j
public class AccountEventHandlerService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    //executer auto lorsqu'il y'aura un event de type .....
    @EventHandler//pour projecter une donnees de l'eventStore vers la BD de lecture
    public void on(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventMessage) {
        log.info("************************");
        log.info("AccountRepository received");

        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getBalance());
        account.setStatus(event.getStatus());
        account.setCurrency(event.getCurrency());
        account.setCreatedAt(eventMessage.getTimestamp());
        accountRepository.save(account);

    }

    @QueryHandler//lire une donnnee
    public List<Account> on(GetAllAccounts query) {

        return accountRepository.findAll();

    }





}
