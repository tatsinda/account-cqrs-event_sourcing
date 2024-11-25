package com.demo.AccountAxon.query.service;

import lombok.AllArgsConstructor;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.stereotype.Component;

//utiliser pour rejouer les evenements au demarrage soit de zero soit a partir d'une date quelconque
@Component
@AllArgsConstructor
public class ReplayEventsService {

    private EventProcessingConfiguration eventProcessingConfiguration;

    public void replay() {
        String name  = "com.demo.AccountAxon.query.service";//on indique le package pour lequel on veut rejouer
        eventProcessingConfiguration.eventProcessor(name, TrackingEventProcessor.class)
                .ifPresent(trackingEventProcessor -> {
                    trackingEventProcessor.shutDown();
                    trackingEventProcessor.resetTokens();
                    trackingEventProcessor.start();
                });
    }



}
