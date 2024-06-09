package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Services.OrderServices;
import org.aspectj.weaver.ast.Or;
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

    @PostMapping("create")
    //method to create the order
    public Order create(Order order) {
        return orderServices.create(order);
    }

    //method to update order
    @PutMapping("update")
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
    public List<OrderDTO> getAllOrders() {
        return orderServices.getOrder();
    }

    @GetMapping("getById")
    public Order getOrderById(@RequestParam Integer orderID){
        return orderServices.getOrderById(orderID);
    }

    @GetMapping("getByCategory")
    public List<Order> getOrderByCategoryName(@RequestParam String categoryName){
        return orderServices.getOrderByCategoryName(categoryName);
    }

    @GetMapping("getByStatus")
    public List<Order> getOrderByStatus(@RequestParam OrderStatus status){
        return orderServices.getOrderByStatus(status);
    }

    @GetMapping("getByPaymentStatus")
    public List<Order> getOrderByPaymentStatus(@RequestParam PaymentStatus paymentStatus){
        return orderServices.getOrderByPaymentStatus(paymentStatus);
    }

    @GetMapping("getByPaymentType")
    public List<Order> getOrderByPaymentStatus(@RequestParam PaymentType paymentType){
        return orderServices.getOrderByPaymentType(paymentType);
    }

}