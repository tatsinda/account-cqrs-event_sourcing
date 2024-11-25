package com.demo.AccountAxon.commandApi.commands;

import lombok.Getter;

//les commandes sont imuables
//commande pour crediter
public class CreditAccoundCommand extends BaseCommand<String>{
    @Getter private String currency;
    @Getter private double amount;

    public CreditAccoundCommand(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
