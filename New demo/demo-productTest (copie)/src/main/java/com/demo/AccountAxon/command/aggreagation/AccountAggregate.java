package com.demo.AccountAxon.command.aggreagation;


import com.demo.AccountAxon.commandApi.commands.CreateAccoundCommand;
import com.demo.AccountAxon.commandApi.commands.CreditAccoundCommand;
import com.demo.AccountAxon.commandApi.commands.DebitAccoundCommand;
import com.demo.AccountAxon.commandApi.enums.AccountStatus;
import com.demo.AccountAxon.commandApi.events.AccountCreatedEvent;
import com.demo.AccountAxon.commandApi.events.AccountCreditedEvent;
import com.demo.AccountAxon.commandApi.events.AccountDebitedEvent;
import com.demo.AccountAxon.commandApi.exception.NegativeInitialBalanceException;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

//il va resumer l'etat actuel de notre application et nous permettre de prendre des decisions
@Aggregate
@NoArgsConstructor //exiger par Axon
public class AccountAggregate {

    @AggregateIdentifier
    private String accountId;
    private String currency;//si on veut changer la devise
    private double balance; //si on veut changer le solde
    private AccountStatus status;


    //ce constructeur sera appele quand on va recevoir une commande de CreateAccountCommand: fonction de decision
    @CommandHandler //un subscribe, il represente la fonction de decision pour la creation d'un compten6
    public AccountAggregate(CreateAccoundCommand command) {

        //verification
        if (command.getInitialBalance() < 0)throw new NegativeInitialBalanceException("negative balance");
        //si tout va bien apres verification alors on peut publier un evenement
        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        command.getId(),
                        command.getCurrency(),
                        command.getInitialBalance(),
                        AccountStatus.CREATED
                )
        );//l'evenement est publie dans le event store

    }

    //fonction d'evolution//pas de regle metier
    @EventSourcingHandler//executer apres modification du eventStore pour mutter l'etat de l'application dans la Base de donnee
    public void on(AccountCreatedEvent event) {

        this.accountId = event.getId();
        this.balance = event.getBalance();
        this.currency = event.getCurrency();
        this.status = event.getStatus();

    }
    @CommandHandler
    public void handle(CreditAccoundCommand command) {
        if (command.getAmount() < 0)throw new NegativeInitialBalanceException("negative Amount");
        AggregateLifecycle.apply(
                new AccountCreditedEvent(
                        command.getId(),
                        command.getCurrency(),
                        command.getAmount()
                )
        );
    }

    //fonction d'evolution
    @EventSourcingHandler//executer apres modification du eventStore pour mutter l'etat de l'application dans la Base de donnee
    public void on(AccountCreditedEvent event) {
        //on change uniquement la balance
        this.balance += event.getAmount() ;

    }

    @CommandHandler
    public void handle(DebitAccoundCommand command) {
        if (command.getAmount() < 0)throw new NegativeInitialBalanceException("negative Amount");
        if (command.getAmount() > this.balance)throw new RuntimeException("Balance insufficient exception");

        AggregateLifecycle.apply(
                new AccountDebitedEvent(
                        command.getId(),
                        command.getCurrency(),
                        command.getAmount()
                )
        );
    }

    //fonction d'evolution
    @EventSourcingHandler//executer apres modification du eventStore pour mutter l'etat de l'application dans la Base de donnee
    public void on(AccountDebitedEvent event) {
        //on change uniquement la balance
        this.balance -= event.getAmount() ;

    }

}
