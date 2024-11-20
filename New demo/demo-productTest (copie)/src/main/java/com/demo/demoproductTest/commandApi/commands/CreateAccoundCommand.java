package com.demo.demoproductTest.commandApi.commands;

import lombok.Getter;

//les commandes sont imuables
public class CreateAccoundCommand extends BaseCommand<String>{
    @Getter private String currency;
    @Getter private double initialBalance;

    public CreateAccoundCommand(String id, String currency, double initialBalance) {
        super(id);
        this.currency = currency;
        this.initialBalance = initialBalance;
    }
}
