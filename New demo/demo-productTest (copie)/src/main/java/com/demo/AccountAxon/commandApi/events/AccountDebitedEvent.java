package com.demo.AccountAxon.commandApi.events;

import com.demo.AccountAxon.commandApi.enums.AccountStatus;
import lombok.Getter;

//l'evenement est nomme au passe. cet evenement est pour la creation d'un compte
public class AccountDebitedEvent extends BaseEvent<String>{

    //ce qu'on va garder dans l'evenement
   @Getter
   private String currency;
    @Getter private double amount;

    public AccountDebitedEvent(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
