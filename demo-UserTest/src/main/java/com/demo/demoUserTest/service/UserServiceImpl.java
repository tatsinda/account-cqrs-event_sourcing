package com.demo.demoUserTest.service;

import com.demo.demoUserTest.dao.UserRepository;
import com.demo.demoUserTest.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User u) {
        User user = userRepository.save(new User(u.getNom(),u.getPrenom(), new ArrayList<>()));
        log.info("user save ....");
        return user;
    }

    @Override
    public Boolean deleteUser(String idUser) {
        Optional<User> user = userRepository.findById(idUser);
        Boolean value = false;
        if (user.isEmpty())
        {
            value = false;
            log.error("User don't exit...");
        }else {
            userRepository.delete(user.get());
            log.info("User delete...");
            value = true;
        }

        return  value;
    }


}
