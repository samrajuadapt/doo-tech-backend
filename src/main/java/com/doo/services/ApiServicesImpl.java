package com.doo.services;

import com.doo.models.Message;
import org.jboss.logging.Logger;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * Created by Sam Raju on 31/12/2021
 */

@Service
public class ApiServicesImpl implements ApiServices{

    private static Logger logger = Logger.getLogger(ApiServicesImpl.class.getName());

    @MessageMapping("/sent")
    @SendTo("/receive")
    public Message sendMessageViaSocket(Message message) throws Exception{
        logger.info("Socket Message Received "+message.getMessage());
        return message;
    }

    @JmsListener(destination = "${ibm.queueName}")
    public void receiveMessage(String data) {
        try {
            logger.info("Ibm Mq Message Received "+data);
        } catch (JmsException ex) {
            ex.printStackTrace();
        }

    }
}
