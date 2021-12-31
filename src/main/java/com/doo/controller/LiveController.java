package com.doo.controller;

import com.doo.models.Message;
import com.doo.models.SortBody;
import com.doo.models.Success;
import com.doo.models.User;
import com.doo.services.ApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
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

    @Autowired
    private ApiServices apiServices;


    @GetMapping
    public ResponseEntity<Success> getSuccess() {
        return ResponseEntity.ok(new Success("Api Deployed Successfully"));
    }

    /*****************
     * WEB SOCKET API
     * ***************/

    @GetMapping("/socket/sent/test")
    public ResponseEntity<Success> sendTest() {
        return sendMessageViaApi(new Message("Sam", "Test", "Test message"));
    }

    @PostMapping("/socket/sent")
    public ResponseEntity<Success> sendMessageViaApi(@RequestBody Message message) {
        try {
            simpMessagingTemplate.convertAndSend("/receive", message);
            return ResponseEntity.ok(new Success("Message Sent Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Success("Message Sent Successfully"));
        }
    }

    /**********
     * IBM MQ MESSAGE QUEUE
     *********/

    @PostMapping("/ibm/sent")
    public ResponseEntity<Success> sendIbmMqMessage(@RequestParam String message) {
        jmsTemplate.convertAndSend(queueName, "Test Data Issued on: " + new Date());
        return ResponseEntity.ok(new Success("Message Sent Successfully"));
    }

    /*********
     * SPRING IOC
     *********/
    @PostMapping("/ioc/sort")
    public ResponseEntity<Success> exchangeSortAlgorithm(@RequestBody SortBody body) {
        int[] sortedNumbers = apiServices.sortNumbers(body.getNumbers());
        return ResponseEntity.ok(new Success("Message Sent Successfully", sortedNumbers));
    }

    /*********
     * SPRING SQL Transactions
     *********/


    @PostMapping("/sql/saveUser")
    public ResponseEntity<Success> saveUser(@RequestBody User user) {
        try {
            apiServices.saveUser(user);
            return ResponseEntity.ok(new Success("User Saved Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Success("Error Occur "+e.getMessage()));
        }
    }

    @GetMapping("/sql/findAll")
    public ResponseEntity<Success> findAll() {
        try {
            return ResponseEntity.ok(new Success("All Users fetched"
                    , apiServices.findAllUsers()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Success("Error Occur "+e.getMessage()));
        }
    }

    @GetMapping("/sql/findUser/{userId}")
    public ResponseEntity<Success> findUser(@PathVariable("userId") int id) {
        try {
            return ResponseEntity.ok(new Success("User fetched"
                    , apiServices.findUser(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Success("Error Occur "+e.getMessage()));
        }
    }

    @DeleteMapping("/sql/delete/{userId}")
    public ResponseEntity<Success> deleteUser(@PathVariable int userId) {
        try {
            apiServices.deleteUser(userId);
            return ResponseEntity.ok(new Success("User Deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Success("Error Occur "+e.getMessage()));
        }
    }
}
