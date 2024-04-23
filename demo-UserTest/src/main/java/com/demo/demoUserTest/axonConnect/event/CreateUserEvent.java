package com.demo.demoUserTest.axonConnect.event;

import com.demo.demoUserTest.pojo.Product;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserEvent {

    private String id;
    private String nom;
    private String prenom;
    private List<Product> products;

}
