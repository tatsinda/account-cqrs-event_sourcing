package com.demo.AccountAxon.commandApi.commands;

import lombok.Getter;

//les commandes sont imuables
//commande pour debiter
public class DebitAccoundCommand extends BaseCommand<String>{
    @Getter private String currency;
    @Getter private double amount;

    public DebitAccoundCommand(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
