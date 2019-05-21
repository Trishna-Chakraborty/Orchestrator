package com.example.orchestrator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SagaController {

    @GetMapping("/sagas")
    public void sagaStart(){
        System.out.println("There");
        new SagaOrchestrator();
        System.out.println("here");

    }
}
