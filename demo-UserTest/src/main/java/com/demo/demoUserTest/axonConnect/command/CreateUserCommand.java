package com.demo.demoUserTest.axonConnect.command;


import com.demo.demoUserTest.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

//creation d'une commande de creation du user
@Data
@NoArgsConstructor

public class CreateUserCommand {

    @TargetAggregateIdentifier
    private String id;
    private String nom;
    private String prenom;
    private List<Product> products;

}
