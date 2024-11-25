package com.demo.AccountAxon.query.controller;

import com.demo.AccountAxon.query.dto.AccountDTO;
import com.demo.AccountAxon.query.dto.AccountHistoryDTO;
import com.demo.AccountAxon.query.dto.AccountTransactionDTO;
import com.demo.AccountAxon.query.entity.Account;
import com.demo.AccountAxon.query.entity.AccountTransaction;
import com.demo.AccountAxon.query.queries.GetAccountByIdQuery;
import com.demo.AccountAxon.query.queries.GetAllAccountHistory;
import com.demo.AccountAxon.query.queries.GetAllAccountTransactions;
import com.demo.AccountAxon.query.queries.GetAllAccounts;
import com.demo.AccountAxon.query.repository.AccountRepository;
import com.demo.AccountAxon.query.service.ReplayEventsService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
public class AccountQueryController {

    @Autowired
    private QueryGateway queryGateway;
    @Autowired
    private ReplayEventsService replayEventsService;

    @GetMapping("/listAccounts")
    public  CompletableFuture<List<AccountDTO>>  accountList() {
      return queryGateway.query(new GetAllAccounts(), ResponseTypes.multipleInstancesOf(AccountDTO.class));

    }

    @GetMapping(path ="/listAccountTransactions/{accountId}")
    public  CompletableFuture<List<AccountTransactionDTO>>  accountTransactionsList(String accountId) {
        return queryGateway.query(new GetAllAccountTransactions(accountId), ResponseTypes.multipleInstancesOf(AccountTransactionDTO.class));

    }

    @GetMapping(path = "/{accountId}")
    public  CompletableFuture<AccountDTO>  accountList(@PathVariable String accountId) {
        return queryGateway.query(new GetAccountByIdQuery(accountId), AccountDTO.class);//on publie la query

    }

    @GetMapping(path = "/{accountId}/history")
    public  CompletableFuture<AccountHistoryDTO>  getHistory(@PathVariable String accountId) {
        return queryGateway.query(new GetAllAccountHistory(accountId),
                ResponseTypes.instanceOf(AccountHistoryDTO.class));//on publie la query

    }

    @GetMapping("/replayEvents")
    public  String  replayEvents() {

        replayEventsService.replay();
        return "Success playing Events";

    }
}
