package com.demo.AccountAxon.query.entity;

import com.demo.AccountAxon.commandApi.enums.AccountStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;
//partie lecture de l'application: comme un autre MS
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    private String createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    @OneToMany(mappedBy = "account")//relation entre table
    private List<AccountTransaction> transactions;
}
