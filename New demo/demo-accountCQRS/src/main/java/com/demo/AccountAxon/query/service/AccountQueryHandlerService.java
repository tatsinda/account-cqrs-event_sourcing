package com.demo.AccountAxon.query.service;

import com.demo.AccountAxon.commandApi.enums.TransactionType;
import com.demo.AccountAxon.commandApi.events.AccountCreatedEvent;
import com.demo.AccountAxon.commandApi.events.AccountCreditedEvent;
import com.demo.AccountAxon.commandApi.events.AccountDebitedEvent;
import com.demo.AccountAxon.query.dto.AccountDTO;
import com.demo.AccountAxon.query.dto.AccountHistoryDTO;
import com.demo.AccountAxon.query.dto.AccountTransactionDTO;
import com.demo.AccountAxon.query.entity.Account;
import com.demo.AccountAxon.query.entity.AccountTransaction;
import com.demo.AccountAxon.query.mappers.AccountMapper;
import com.demo.AccountAxon.query.queries.GetAccountByIdQuery;
import com.demo.AccountAxon.query.queries.GetAllAccountHistory;
import com.demo.AccountAxon.query.queries.GetAllAccountTransactions;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//creation de la classe service account
@Service
@Transactional
@Slf4j
public class AccountQueryHandlerService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;
  //  private AccountMapper accountMapper;






    @QueryHandler//ecouteur de query et on traite la query du User
    public List<AccountDTO> on(GetAllAccounts query) {

        List<Account> accounts= accountRepository.findAll();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        accounts.forEach(
                p->{
                    accountDTOS.add(new AccountDTO(
                            p.getId(),
                            p.getCreatedAt(),
                            p.getBalance(),
                            p.getStatus(),
                            p.getCurrency()
                    ));
                }
        );


        return accountDTOS;

    }

    @QueryHandler//ecouteur de query et on traite la query du User
    public List<AccountTransactionDTO> on(GetAllAccountTransactions query) {
            List<AccountTransaction> transactionList  = accountTransactionRepository.findByAccountId(query.getId());
        List<AccountTransactionDTO> accountTransactionDTOS = new ArrayList<>();
        transactionList.forEach(p->{
            accountTransactionDTOS.add(new AccountTransactionDTO(
                    p.getId(),
                    p.getTimestamp(),
                    p.getAmount(),
                    p.getType()
            ));
        });
        return accountTransactionDTOS;

    }

    @QueryHandler//ecouteur de query et on traite la query du User
    public AccountDTO on(GetAccountByIdQuery query) {

        log.info("id query :: "+query.getAccountId());
        Account account = accountRepository.findById(query.getAccountId()).get();
        //log.info("account data :: "+account);
        AccountDTO accountDTO = new AccountDTO(
                account.getId(),
                "",
                account.getBalance(),
                account.getStatus(),
                account.getCurrency()

        );

        //AccountDTO accountDTO =accountMapper.fromAccount(account);
        return accountDTO;
    }

    @QueryHandler//ecouteur de query et on traite la query du User
    public AccountHistoryDTO on(GetAllAccountHistory query) {

        log.info("id query :: "+query.getId());
        Account account = accountRepository.findById(query.getId()).get();

        List<AccountTransaction> transactionList  = accountTransactionRepository.findByAccountId(query.getId());
        List<AccountTransactionDTO> accountTransactionDTOS = new ArrayList<>();
        //log.info("account data :: "+account);
        AccountDTO accountDTO = new AccountDTO(
                account.getId(),
                "",
                account.getBalance(),
                account.getStatus(),
                account.getCurrency()

        );

        transactionList.forEach(p->{
            accountTransactionDTOS.add(new AccountTransactionDTO(
                    p.getId(),
                    p.getTimestamp(),
                    p.getAmount(),
                    p.getType()
            ));
        });

        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO(accountDTO,accountTransactionDTOS);

        //AccountDTO accountDTO =accountMapper.fromAccount(account);
        return accountHistoryDTO;
    }

}
