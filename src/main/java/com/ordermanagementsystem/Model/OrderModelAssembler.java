package com.ordermanagementsystem.Model;

import com.ordermanagementsystem.Controller.OrderController;
import com.ordermanagementsystem.Entity.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>>{
    @Override
    public EntityModel<Order> toModel(Order order) {
        return EntityModel.of(order,
                WebMvcLinkBuilder.linkTo(methodOn(OrderController.class).getAllOrders()).withRel("orders"),
                linkTo(methodOn(OrderController.class).getOrder(order.getOrderId())).withSelfRel());
    }
}
