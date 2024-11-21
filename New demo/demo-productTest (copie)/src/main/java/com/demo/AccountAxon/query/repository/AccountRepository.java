package com.demo.AccountAxon.query.repository;

import com.demo.AccountAxon.query.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account,String> {
}
