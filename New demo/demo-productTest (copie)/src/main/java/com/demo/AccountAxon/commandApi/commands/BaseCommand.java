package com.demo.AccountAxon.commandApi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

//classe de base des commands : contenant les donnees similaires a toutes les commandes
public class BaseCommand<T> {
    @TargetAggregateIdentifier//id d'une commande doit obligatoire contenir cette annotation
    @Getter private T id;//une commande est un obet imuable

    public BaseCommand(T id) {
        this.id = id;
    }
}
