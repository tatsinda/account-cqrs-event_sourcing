package com.demo.AccountAxon.controller;

import com.demo.AccountAxon.entity.Customer;
import io.grpc.netty.shaded.io.netty.util.concurrent.CompleteFuture;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers/query")
public class CustomerQueryController {
    @Autowired
    private QueryGateway queryGateway;

    public CompleteFuture<List<Customer>> customers() {

        return queryGateway.query(new GetAllCustomersQuery(),
                ResponseTypes.multipleInstancesOf(Customer.class))
    }


}
