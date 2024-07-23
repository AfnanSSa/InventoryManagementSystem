package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Services.OrderServices;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServices orderServices;
    @Autowired
    SlackService slackService;

    @PostMapping("create")
    //method to create the order
    @TrackExecutionTime
    public Order create(Order order) {
        slackService.sendMessage("afnan", "New order has been added!");
        return orderServices.create(order);
    }

    //method to update order
    @PutMapping("update")
    @TrackExecutionTime
    public <T> ResponseEntity<T> update(@RequestParam Integer id) {
        try {
            String result = orderServices.update(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return (ResponseEntity<T>) new ResponseEntity<>("Updating Failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //method to cancel order
    @PutMapping("cancel")
    @TrackExecutionTime
    public <T> ResponseEntity<T> cancel(@RequestParam Integer id) {
        try {
            String result = orderServices.cancel(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return (ResponseEntity<T>) new ResponseEntity<>("Cancellation failed!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //method to get all orders
    @GetMapping("get")
    @TrackExecutionTime
    public List<OrderDTO> getAllOrders() {
        return orderServices.getOrder();
    }

    @GetMapping("getById")
    @TrackExecutionTime
    public Order getOrderById(@RequestParam Integer orderID){
        return orderServices.getOrderById(orderID);
    }

    @GetMapping("getByCategory")
    @TrackExecutionTime
    public List<Order> getOrderByCategoryName(@RequestParam String categoryName){
        return orderServices.getOrderByCategoryName(categoryName);
    }

    @GetMapping("getByStatus")
    @TrackExecutionTime
    public List<Order> getOrderByStatus(@RequestParam OrderStatus status){
        return orderServices.getOrderByStatus(status);
    }

    @GetMapping("getByPaymentStatus")
    @TrackExecutionTime
    public List<Order> getOrderByPaymentStatus(@RequestParam PaymentStatus paymentStatus){
        return orderServices.getOrderByPaymentStatus(paymentStatus);
    }

    @GetMapping("getByPaymentType")
    @TrackExecutionTime
    public List<Order> getOrderByPaymentStatus(@RequestParam PaymentType paymentType){
        return orderServices.getOrderByPaymentType(paymentType);
    }

}