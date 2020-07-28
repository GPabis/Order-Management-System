package com.ordermanagementsystem.Controller;

import com.ordermanagementsystem.Entity.Order;
import com.ordermanagementsystem.Entity.OrderRepository;
import com.ordermanagementsystem.Exceptions.OrderNotFoundException;
import com.ordermanagementsystem.Model.OrderModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path="/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler orderModelAssembler;

    public OrderController(OrderRepository orderRepository, OrderModelAssembler orderModelAssembler){
        this.orderRepository = orderRepository;
        this.orderModelAssembler = orderModelAssembler;
    }

    @PostMapping(path="/")
    ResponseEntity<?> addNewOrder(@RequestBody Order newOrder){
        EntityModel<Order> entityModel = orderModelAssembler.toModel(orderRepository.save(newOrder));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping(path="/")
    public CollectionModel<EntityModel<Order>> getAllOrders(){
        List<EntityModel<Order>> orders = orderRepository.findAll().stream()
                .map(orderModelAssembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class)
                .getAllOrders()).withSelfRel());
    }

    @GetMapping(path = "/{orderId}")
    public EntityModel<Order> getOrder(@PathVariable Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException(orderId));
        return orderModelAssembler.toModel(order);
    }

    @PutMapping(path = "/{orderId}")
    ResponseEntity<?> updateOrder(@RequestBody Order newOrder, @PathVariable Long orderId){
        Order updateOrder = orderRepository.findById(orderId).map(order -> {
            order.setOrderName(newOrder.getOrderName());
            order.setCustomerName(newOrder.getCustomerName());
            order.setCustomerSurname(newOrder.getCustomerSurname());
            return orderRepository.save(order);
        }).orElseThrow(()-> new OrderNotFoundException(orderId));

        EntityModel<Order> entityModel = orderModelAssembler.toModel(updateOrder);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{orderId}")
    ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
        if(!orderRepository.existsById(orderId)) throw new OrderNotFoundException(orderId);
        orderRepository.deleteById(orderId);
        return ResponseEntity.noContent().build();
    }

}
