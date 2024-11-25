package com.demo.AccountAxon.query.dto;

import com.demo.AccountAxon.commandApi.enums.AccountStatus;
import com.demo.AccountAxon.query.entity.AccountTransaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

//partie lecture de l'application: comme un autre MS

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

    private String id;
    private String createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;

}
