package com.demo.AccountAxon.query.repository;

import com.demo.AccountAxon.query.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<AccountTransaction,Long> {
}
