package com.demo.digitalAxon;

import com.demo.digitalAxon.command.aggreagation.AccountAggregate;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.axonframework.modelling.command.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitalAxonApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalAxonApplication.class, args);
	}





}
