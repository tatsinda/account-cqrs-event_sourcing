package com.demo.AccountAxon.query.dto;

import com.demo.AccountAxon.commandApi.enums.AccountStatus;
import com.demo.AccountAxon.commandApi.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//partie lecture de l'application: comme un autre MS

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTransactionDTO {

    private int id;
    private String timestamp;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

}
