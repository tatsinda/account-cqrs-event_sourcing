package com.demo.AccountAxon.query.entity;

import com.demo.AccountAxon.commandApi.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransaction {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String timestamp;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @ManyToOne
    private Account account;
}
