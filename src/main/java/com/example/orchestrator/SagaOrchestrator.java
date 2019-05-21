package com.example.orchestrator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class SagaOrchestrator {


    private List<String> sagaSteps;
    public  SagaOrchestrator(){
        sagaSteps= new ArrayList<>();
        sagaSteps.add("customer");
        sagaSteps.add("bank");
        sagaSteps.add("order");
        try (RPCClient rpcClient = new RPCClient()) {
            rpcClient.getChannel().queueDeclare(rpcClient.getReplyQueueName(), false, false, false, null);
            new DeadLetterHandlingThread("dead_queue");
            String request,response;
            request="100";
            for(String sagaStep: sagaSteps){
                response=rpcClient.call(request,sagaStep+"_exchange",sagaStep,rpcClient.getReplyQueueName());

                if(response.equals("exception")){
                    System.out.println("Exception Occured in "+ sagaStep);
                    break;
                }
                else request=response;
                System.out.println("Sent message  and got response from "+sagaStep + " : "+response);
            }
            /*String response = rpcClient.call("100","customer_exchange","customer",rpcClient.getReplyQueueName());
            System.out.println("Sent message  and got response from customer "+response);
            rpcClient.call(response,"bank_exchange","bank",rpcClient.getReplyQueueName());
            System.out.println("Sent message  and got response from order "+response);*/




        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
