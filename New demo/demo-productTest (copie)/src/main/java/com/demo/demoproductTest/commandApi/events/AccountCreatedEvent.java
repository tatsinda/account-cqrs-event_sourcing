package com.demo.demoproductTest.commandApi.events;

import com.demo.demoproductTest.commandApi.enums.AccountStatus;
import lombok.Getter;

//l'evenement est nomme au passe. cet evenement est pour la creation d'un compte
public class AccountCreatedEvent extends BaseEvent<String>{

    //ce qu'on va garder dans l'evenement
   @Getter
   private String currency;
    @Getter private double balance;
    @Getter private AccountStatus status;

    public AccountCreatedEvent(String id,String currency,double balance,
                               AccountStatus status) {
        super(id);
        this.currency = currency;
        this.balance = balance;
        this.status = status;
    }
}
