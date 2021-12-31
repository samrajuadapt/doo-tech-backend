package com.doo.controller;

import com.doo.models.Message;
import com.doo.models.Success;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Sam Raju on 31/12/2021
 */
@RestController
public class LiveController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${ibm.queueName}")
    private String queueName;


    @GetMapping
    public ResponseEntity<Success> getSuccess(){
        return ResponseEntity.ok(new Success("Api Deployed Successfully"));
    }

    /*****************
     * WEB SOCKET API
     * ***************/

    @GetMapping("/socket/sent/test")
    public ResponseEntity<Success> sendTest(){
        return sendMessageViaApi(new Message("Sam","Test","Test message"));
    }

    @PostMapping("/socket/sent")
    public ResponseEntity<Success> sendMessageViaApi(@RequestBody Message message){
        try {
            simpMessagingTemplate.convertAndSend("/receive",message);
            return ResponseEntity.ok(new Success("Message Sent Successfully"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Success("Message Sent Successfully"));
        }
    }

    /**********
     * IBM MQ MESSAGE QUEUE
     *********/

    @PostMapping("/ibm/sent")
    public ResponseEntity<Success> sendIbmMqMessage(@RequestParam String message){
        jmsTemplate.convertAndSend(queueName, "Test Data Issued on: "+new Date());
        return ResponseEntity.ok(new Success("Message Sent Successfully"));
    }
    
}
