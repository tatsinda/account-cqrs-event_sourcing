package com.demo.demoUserTest.axonConnect.query.handler;

import com.demo.demoUserTest.axonConnect.query.GetAllUserQuery;
import com.demo.demoUserTest.axonConnect.query.GetUserByIdQuery;
import com.demo.demoUserTest.dao.UserRepository;
import com.demo.demoUserTest.model.User;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//gestionnaire des requettes
@Component
@Slf4j
public class UserQueryHandler {


    @Autowired
    private UserRepository userRepository;

    //interaction directe avec la base de donnee
    @QueryHandler //pour gerer la requette
    public List<User> getAllUser(GetAllUserQuery query) {

        return userRepository.findAll();
    }

    //interaction directe avec la base de donnee
    @QueryHandler
    public User getUser(GetUserByIdQuery getUserByIdQuery) {
        return Optional.ofNullable(userRepository.findById(getUserByIdQuery.getId()).orElseThrow(() -> new RuntimeException("Not found"))).get();
    }

}
