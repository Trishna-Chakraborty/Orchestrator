package com.example.orchestrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class SagaController {


    @Autowired
    ServiceHostMappingRepository serviceHostMappingRepository;
    @GetMapping("/sagas")
    public void sagaStart(){
        new SagaOrchestrator();
    }



    @GetMapping(value = "orchestrator/{serviceName}/**" )
    public ResponseEntity<String> redirectGetRequest(@PathVariable("serviceName")String serviceName, HttpServletRequest request){

        String uri=request.getRequestURI();
        String temp[]=uri.split("/",4);
        RestTemplate restTemplate= new RestTemplate();
        return restTemplate.getForEntity(serviceHostMappingRepository.getServiceHostMappingByServiceName(serviceName)+temp[3],String.class);

    }


    @PostMapping(value = "orchestrator/{serviceName}/**" )
    public void handlePostRequest(@RequestBody String json, HttpServletRequest request){

        System.out.println(json);
        try (RPCClient rpcClient = new RPCClient()) {
            new DeadLetterHandlingThread("dead_queue");
            String response = rpcClient.emitMessage(json,"customer_exchange","save");
            System.out.println("Sent message  and got response from customer "+response);


            /*String response = rpcClient.call("100","customer_exchange","customer",rpcClient.getReplyQueueName());
            System.out.println("Sent message  and got response from customer "+response);
            rpcClient.call(response,"bank_exchange","bank",rpcClient.getReplyQueueName());
            System.out.println("Sent message  and got response from order "+response);*/




        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }

/*        String uri=request.getRequestURI();
        request.get
        String temp[]=uri.split("/",4);
        RestTemplate restTemplate= new RestTemplate();
        return restTemplate.getForEntity(serviceHostMappingRepository.getServiceHostMappingByServiceName(serviceName)+temp[3],String.class);*/

    }

}
