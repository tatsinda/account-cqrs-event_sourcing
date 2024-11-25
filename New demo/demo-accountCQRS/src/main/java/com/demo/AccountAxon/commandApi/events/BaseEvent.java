package com.demo.AccountAxon.commandApi.events;

import lombok.Getter;

public class BaseEvent<T>{

    @Getter private String id;
    public BaseEvent(String id) {
        this.id = id;
    }


}
