package com.demo.demoUserTest.service;

import com.demo.demoUserTest.model.User;

public interface UserService {

    public User createUser(User user);
    public Boolean deleteUser(String idUser);

}
