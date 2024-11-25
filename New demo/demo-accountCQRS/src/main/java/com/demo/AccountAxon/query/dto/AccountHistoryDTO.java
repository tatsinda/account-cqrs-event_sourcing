package com.demo.AccountAxon.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountHistoryDTO {

    private AccountDTO accountDTO;
    private List<AccountTransactionDTO> accountTransactionDTOS;
}
