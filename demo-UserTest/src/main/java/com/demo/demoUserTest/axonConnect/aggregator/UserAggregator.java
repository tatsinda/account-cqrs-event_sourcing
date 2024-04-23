package com.demo.demoUserTest.axonConnect.aggregator;

import com.demo.demoUserTest.axonConnect.command.CreateUserCommand;
import com.demo.demoUserTest.axonConnect.event.CreateUserEvent;
import com.demo.demoUserTest.pojo.Product;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Aggregate
@NoArgsConstructor
//creation de l'agregateur
public class UserAggregator {
    @AggregateIdentifier
    private String userId;
    private String nom;
    private String prenom;
    private List<Product> products;

    //fonction de decision ou metier
    @CommandHandler //on precise qu'on gere la commande ici
    public UserAggregator(CreateUserCommand command) { //on obtiens la commande puis on configure

        //creation de l'evenement
        CreateUserEvent event  = new CreateUserEvent();
        event.setId(command.getId());
        event.setNom(command.getNom());
        event.setPrenom(command.getPrenom());
        event.setProducts(command.getProducts());

        //ecrire ici n'importe quelle logique metier: par exemple si le nom est vide alors ....



        AggregateLifecycle.apply(event); //on fait appel a la fonction d'evolution : EventSourcingHandler

    }

    //fonction d evolution :   insertion  des donnes dans l'event store puis on publier au travers de l'event bus les donnees  danms l'event handler
    @EventSourcingHandler
    public void createUserEvent(CreateUserEvent event)
    {

        this.userId = event.getId();
        this.nom = event.getNom();
        this.prenom = event.getPrenom();
        this.products = event.getProducts();
    }

}
