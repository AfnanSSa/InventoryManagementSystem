package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    public Order globalOrder = new Order();

    //method to create the order
    @PostMapping("create")
    public Order create(){
        Order userOrder = new Order();

        Product product = new Product();

        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(1);
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setCreatedDate(new Date());

        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
        product.setId(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        userOrder.setId(01);
        userOrder.setProductsOrdered(Arrays.asList(product)); //setting the products lists
        userOrder.setCategoryName("Electronics");
        userOrder.setCreatedDate(new Date());
        userOrder.setOrderDate(new Date());
        userOrder.setStatus(OrderStatus.IN_PROGRESS);
        userOrder.setPaymentStatus(PaymentStatus.PAID);
        userOrder.setPaymentType(PaymentType.BANK_TRANSFER);
        userOrder.setDueDate(new Date());

        globalOrder = userOrder;
        return userOrder;
    }

    //method to update order
    @PutMapping("update")
    public Order update(@RequestBody Order userOrder){

        userOrder.setOrderDate(new Date());
        userOrder.setId(04);

        globalOrder = userOrder;
        return userOrder;

    }

    //method to cancel order
    @PutMapping("cancel/{id}")
    public String cancel(@PathVariable Integer id, Order userOrder){
        if (userOrder != null && userOrder.getStatus() == OrderStatus.IN_PROGRESS){
            userOrder.setStatus(OrderStatus.CANCELED);
            if (userOrder.getPaymentStatus() == PaymentStatus.PAID){
                userOrder.setPaymentStatus(PaymentStatus.REFUND);
            }
            return "Order " + id + " cancelled successfully";
        }
        return "Cancellation failed";
    }
}
