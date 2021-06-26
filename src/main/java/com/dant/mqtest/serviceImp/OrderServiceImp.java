/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dant.mqtest.serviceImp;

import com.dant.mqtest.model.OrderRequest;
import com.dant.mqtest.model.OrderResponse;
import com.dant.mqtest.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private JmsTemplate jmsTemplate;
    
    private static final Logger LOG = Logger.getLogger(OrderServiceImp.class.getName());
    
    @Override
    public OrderRequest sendMessage(OrderRequest request) {
       ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(request);
            LOG.log(Level.INFO, json);
            jmsTemplate.convertAndSend("DEV.QUEUE.3", json);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(OrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }catch(JmsException ex){
            ex.printStackTrace();
        }
       return request;
    }   

    @Override
    public OrderResponse readMessage() {
        OrderResponse orderResponse = new OrderResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonResponse =  jmsTemplate.receiveAndConvert("DEV.QUEUE.3").toString();
            LOG.log(Level.INFO, jsonResponse);
            orderResponse = mapper.readValue(jsonResponse,OrderResponse.class);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(OrderServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }catch(JmsException ex){
            ex.printStackTrace();
        }
        return orderResponse;
    }
}
