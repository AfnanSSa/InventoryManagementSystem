package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.AOP.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServices {
    @Autowired
    OrderRepository orderRepository; //reference to Order repo
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;

    OrderDTO orderDTO;

    //method to create the order
    @TrackExecutionTime
    public Order create(Order order){

        Product product = new Product();

        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setCreatedDate(new Date());
        productDetails = productDetailsRepository.save(productDetails);

        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());
        product = productRepository.save(product);

        order.setProductsOrdered(Arrays.asList(product)); //setting the products lists
        order.setCategoryName("Electronics");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());

        return orderRepository.save(order);
    }

    //method to update order
    @TrackExecutionTime
    public String update(Integer id){
        Order order = orderRepository.getById(id);
        order.setOrderDate(new Date());
        orderRepository.save(order);
        return "Updated successfully";
    }

    //method to cancel order
    @TrackExecutionTime
    public String cancel(Integer id){
        Order order = orderRepository.getById(id);
        order.setPaymentStatus(PaymentStatus.REFUND);
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);

        return "Order " + id + " cancelled successfully";
    }


    @TrackExecutionTime
    public List<OrderDTO> getOrder(){
        List<Order> orders = orderRepository.findAll();
        return orderDTO.convertToDTOList(orders);
    }

    @TrackExecutionTime
    public Order getOrderById(Integer orderID){
        return orderRepository.getOrderById(orderID);
    }

    @TrackExecutionTime
    public List<Order> getOrderByCategoryName(String categoryName){
        return orderRepository.getOrderByCategoryName(categoryName);
    }

    @TrackExecutionTime
    public List<Order> getOrderByStatus(OrderStatus status){
        return orderRepository.getOrderByStatus(status);
    }

    @TrackExecutionTime
    public List<Order> getOrderByPaymentStatus(PaymentStatus paymentStatus){
        return orderRepository.getOrderByPaymentStatus(paymentStatus);
    }

    @TrackExecutionTime
    public List<Order> getOrderByPaymentType(PaymentType paymentType){
        return orderRepository.getOrderByPaymentType(paymentType);
    }
}
