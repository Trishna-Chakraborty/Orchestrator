package com.example.orchestrator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class SagaOrchestrator {


    private List<String> events;
    public  SagaOrchestrator(){
        events= new ArrayList<>();
        events.add("customerBalance");
        try (RPCClient rpcClient = new RPCClient()) {
           rpcClient.getChannel().queueDeclare(rpcClient.getReplyQueueName(), false, false, false, null);
           String response = rpcClient.call("100","customer",rpcClient.getReplyQueueName());
            System.out.println("Sent message  and get response from customer "+response);
            rpcClient.call(response,"order",rpcClient.getReplyQueueName());
            System.out.println("Sent message  and get responsen from order "+response);




        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
