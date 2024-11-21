package com.demo.AccountAxon.commandApi.commands;

import lombok.Getter;

//les commandes sont imuables
//commande pour creer un compte
public class CreateAccoundCommand extends BaseCommand<String>{
    @Getter private String currency;
    @Getter private double initialBalance;

    public CreateAccoundCommand(String id, String currency, double initialBalance) {
        super(id);
        this.currency = currency;
        this.initialBalance = initialBalance;
    }
}
