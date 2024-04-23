package com.demo.demoUserTest.model;

import com.demo.demoUserTest.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private List<Product> products = new ArrayList<>();

    public User(String nom, String prenom, List<Product> products) {
        this.nom = nom;
        this.prenom = prenom;
        this.products = products;
    }



}
