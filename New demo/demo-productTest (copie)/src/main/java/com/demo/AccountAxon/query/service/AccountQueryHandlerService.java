package com.demo.AccountAxon.query.service;

import com.demo.AccountAxon.commandApi.enums.TransactionType;
import com.demo.AccountAxon.commandApi.events.AccountCreatedEvent;
import com.demo.AccountAxon.commandApi.events.AccountCreditedEvent;
import com.demo.AccountAxon.commandApi.events.AccountDebitedEvent;
import com.demo.AccountAxon.query.entity.Account;
import com.demo.AccountAxon.query.entity.AccountTransaction;
import com.demo.AccountAxon.query.queries.GetAccountByIdQuery;
import com.demo.AccountAxon.query.queries.GetAllAccounts;
import com.demo.AccountAxon.query.repository.AccountRepository;
import com.demo.AccountAxon.query.repository.AccountTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

//creation de la classe service account
@Service
@Transactional
@Slf4j
public class AccountEventHandlerService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;



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

    //executer auto lorsqu'il y'aura un event de type .....
    @EventHandler//pour projecter une donnees de l'eventStore vers la BD de lecture
    public void on(AccountCreditedEvent event) {
        log.info("************************");
        log.info("AccountCreditedEvent received");

        Account account = accountRepository.findById(event.getId()).get();

        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAmount(event.getAmount());
        accountTransaction.setTimestamp(new Date());
        accountTransaction.setType(TransactionType.CREDIT);
        accountTransaction.setAccount(account);

        accountTransactionRepository.save(accountTransaction);

        account.setBalance(account.getBalance() + event.getAmount());
        //account.getTransactions().add(accountTransaction);

        accountRepository.save(account);

    }

    //executer auto lorsqu'il y'aura un event de type .....
    @EventHandler//pour projecter une donnees de l'eventStore vers la BD de lecture
    public void on(AccountDebitedEvent event) {
        log.info("************************");
        log.info("AccountDebitedEvent received");

        Account account = accountRepository.findById(event.getId()).get();

        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAmount(event.getAmount());
        accountTransaction.setTimestamp(new Date());
        accountTransaction.setType(TransactionType.DEBIT);
        accountTransaction.setAccount(account);

        accountTransactionRepository.save(accountTransaction);

        account.setBalance(account.getBalance() - event.getAmount());
       // account.getTransactions().add(accountTransaction);

        accountRepository.save(account);
    }

    @QueryHandler//lire une donnnee et on traite la query du User
    public List<Account> on(GetAllAccounts query) {

        return accountRepository.findAll();

    }

    @QueryHandler//lire une donnnee et on traite la query du User
    public Account on(GetAccountByIdQuery query) {

        return accountRepository.findAll();

    }

    @ResetHandler//pour executer cette methode en premiere lorsquOn rejout les event //le but est de supprimer toute la BD avant de la reconstituer a partir de l'eventStore
    public void resetDataBase() {

        log.info("reset dataBase......");
        accountRepository.deleteAll();
        accountTransactionRepository.deleteAll();


    }





}
