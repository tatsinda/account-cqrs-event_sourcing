package com.demo.AccountAxon.query.repository;

import com.demo.AccountAxon.query.entity.AccountTransaction;
import jakarta.persistence.EnumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {

    List<AccountTransaction> findByAccountId(String id);
}
