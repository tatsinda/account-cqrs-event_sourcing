package com.demo.AccountAxon.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetAccountByIdQuery {
    private String accountId;
}
