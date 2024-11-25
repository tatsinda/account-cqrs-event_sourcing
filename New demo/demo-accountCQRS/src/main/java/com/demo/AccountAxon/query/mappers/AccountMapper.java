package com.demo.AccountAxon.query.mappers;

import com.demo.AccountAxon.query.dto.AccountDTO;
import com.demo.AccountAxon.query.entity.Account;

//utiliser pour faire le mapping objet objet
//@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO fromAccount(Account account);
    Account fromAccountDTO(AccountDTO accountDTO);


}
