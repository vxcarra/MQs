package com.dant.mqtest.service;

import com.dant.mqtest.model.OrderRequest;
import com.dant.mqtest.model.OrderResponse;

public interface OrderService {
    OrderRequest sendMessage(OrderRequest request);
    OrderResponse readMessage(); 
}
