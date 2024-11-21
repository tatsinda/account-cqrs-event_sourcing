package com.demo.AccountAxon.query.controller;

import com.demo.AccountAxon.query.entity.Account;
import com.demo.AccountAxon.query.queries.GetAllAccounts;
import com.demo.AccountAxon.query.repository.AccountRepository;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/account")
public class AccountQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/list")
    public  CompletableFuture<List<Account>>  accountList() {
      return queryGateway.query(new GetAllAccounts(), ResponseTypes.multipleInstancesOf(Account.class));

    }
}
