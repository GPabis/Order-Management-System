package com.example.ordermanagementsystem.Model;

import com.example.ordermanagementsystem.Controller.MainController;
import com.example.ordermanagementsystem.Entity.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>>{
    @Override
    public EntityModel<Order> toModel(Order order) {
        return EntityModel.of(order,
                linkTo(methodOn(MainController.class).getAllOrders()).withRel("orders"),
                linkTo(methodOn(MainController.class).getOneOrder(order.getOrderId())).withSelfRel());
    }
}
