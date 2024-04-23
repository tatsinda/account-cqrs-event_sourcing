package com.demo.demoUserTest.axonConnect.eventHandler;

import com.demo.demoUserTest.axonConnect.event.CreateUserEvent;
import com.demo.demoUserTest.dao.UserRepository;
import com.demo.demoUserTest.model.User;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//creation du gestion d'evenement pour gerer l'evenement qui a ete cree
@Component
public class UserEventHandler {

    @Autowired
    private UserRepository userRepository;

    //gestion de l'evenement de creation d'un User
    //pour l'enregistrer dans la BD
    @EventHandler
    public void createUser(CreateUserEvent event)
    {
        User user = new User();
        user.setId(event.getId());
        user.setNom(event.getNom());
        user.setPrenom(event.getPrenom());
        user.setProducts(event.getProducts());

        userRepository.save(user);//enregistrement dans la BD
    }


}
