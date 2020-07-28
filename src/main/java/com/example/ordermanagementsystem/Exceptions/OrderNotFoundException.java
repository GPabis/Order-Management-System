package com.example.ordermanagementsystem.Exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(long orderId) {super("Could not find order " + orderId);}
}
