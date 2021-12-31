package com.doo.services;

import com.doo.dao.UserDao;
import com.doo.models.Message;
import com.doo.models.User;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sam Raju on 31/12/2021
 */

@Service
public class ApiServicesImpl implements ApiServices {

    private static Logger logger = Logger.getLogger(ApiServicesImpl.class.getName());

    @Autowired
    private UserDao userDao;

    /*****************
     * WEB SOCKET API
     * ***************/

    @MessageMapping("/sent")
    @SendTo("/receive")
    public Message sendMessageViaSocket(Message message) throws Exception {
        logger.info("Socket Message Received " + message.getMessage());
        return message;
    }

    /**********
     * IBM MQ MESSAGE QUEUE
     *********/

    @JmsListener(destination = "${ibm.queueName}")
    public void receiveMessage(String data) {
        try {
            logger.info("Ibm Mq Message Received " + data);
        } catch (JmsException ex) {
            ex.printStackTrace();
        }

    }

    /*********
     * SPRING IOC
     *********/

    @Override
    public int[] sortNumbers(int[] numbers) {
        int temp;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        return numbers;
    }

    /*********
     * SPRING ORM USING JDBC
     *********/

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User findUser(int id) {
        return userDao.findById(id).get(0);
    }

    @Override
    public void deleteUser(int id) {
        userDao.delete(id);
    }
}
