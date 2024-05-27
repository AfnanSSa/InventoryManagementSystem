package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Services.OrderServices;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String update(@RequestBody Order order) {
        return orderServices.update(order);

    }

    //method to cancel order
    @PutMapping("cancel")
    public String cancel(@RequestParam Integer id) {
        return orderServices.cancel(id);
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

}