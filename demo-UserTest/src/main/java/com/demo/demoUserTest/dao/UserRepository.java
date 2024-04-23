package com.demo.demoUserTest.dao;

import com.demo.demoUserTest.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository  extends MongoRepository<User,String> {
}
