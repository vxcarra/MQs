/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dant.mqtest.controller;

import com.dant.mqtest.model.OrderRequest;
import com.dant.mqtest.model.OrderResponse;
import com.dant.mqtest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dantl
 */
@RequestMapping("mq")
@RestController
public class TestController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest order){
        OrderRequest orderRequest = orderService.sendMessage(order);
        return new ResponseEntity(orderRequest, HttpStatus.ACCEPTED);
    }
    
    @GetMapping(
            produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> readOrder(){
        OrderResponse orderResponse = orderService.readMessage();
        return new ResponseEntity(orderResponse, HttpStatus.ACCEPTED);
    }
    
}
