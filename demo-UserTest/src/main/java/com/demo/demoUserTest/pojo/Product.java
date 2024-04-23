package com.demo.demoUserTest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String idProduct;
    private String nom;
    private String price;
    private String categorie;
    private String idUser;

}
