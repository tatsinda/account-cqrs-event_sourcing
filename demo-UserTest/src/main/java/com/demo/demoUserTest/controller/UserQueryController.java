package com.demo.demoUserTest.controller;

import com.demo.demoUserTest.axonConnect.command.CreateUserCommand;
import com.demo.demoUserTest.axonConnect.query.GetAllUserQuery;
import com.demo.demoUserTest.axonConnect.query.GetUserByIdQuery;
import com.demo.demoUserTest.model.User;
import com.demo.demoUserTest.service.UserServiceImpl;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserQueryController { //controller des requettes

    @Autowired
    private QueryGateway queryGateway; //passerelle de requette: Query bus

    //*****************************Axon server************************//
    @GetMapping(value = "/api/user")
    public List<User> listAllUser()
    {
        GetAllUserQuery getAllUserQuery = new GetAllUserQuery();
        //on envoi la requette a la passerelle
        List<User> userList = queryGateway.query(getAllUserQuery, ResponseTypes.multipleInstancesOf(User.class)).join(); //passer a au Query handler a travers le query bus
            return userList;
    }

    @GetMapping(value = "/api/user/{idUser}")
    public User findUser(@PathVariable String idUser)
    {
        GetUserByIdQuery userQuery = new GetUserByIdQuery();
        userQuery.setId(idUser);
        //on envoi la requette a la passerelle
        User user = queryGateway.query(userQuery, ResponseTypes.instanceOf(User.class)).join();
        return user;
    }


}
