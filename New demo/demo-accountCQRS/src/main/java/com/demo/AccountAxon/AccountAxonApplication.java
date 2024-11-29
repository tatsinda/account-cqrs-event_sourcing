package com.demo.AccountAxon;

import com.demo.AccountAxon.command.aggreagation.AccountAggregate;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.axonframework.modelling.command.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountAxonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountAxonApplication.class, args);
	}

	//@Bean//pour rendre l'aggraga persistant
	public Repository<AccountAggregate> accountAggregateRepository(EntityManagerProvider emp, ParameterResolverFactory prf, EventBus eb){
		return GenericJpaRepository.builder(AccountAggregate.class)
				.entityManagerProvider(emp)
				.parameterResolverFactory(prf)
				.eventBus(eb)
				.build();
	}



}
