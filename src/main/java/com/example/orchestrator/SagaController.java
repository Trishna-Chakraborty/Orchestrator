package com.example.orchestrator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SagaController {

    @GetMapping("/sagas")
    public void sagaStart(){
       /* new ConsumerThread("customer");
        new ConsumerThread("order");*/
        //System.out.println("here");
        new SagaOrchestrator();


    }
}
