package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.*;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    public Supplier globalSupplier = new Supplier();

    @PostMapping("add")
    //method to add supplier
    public Supplier addSupplier() {

        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(1);
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setCreatedDate(new Date());

        Product product = new Product();
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
        product.setId(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        Order order = new Order();
        order.setId(01);
        order.setProductsOrdered(Arrays.asList(product)); //setting the products lists
        order.setCategoryName("Electronics");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());

        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setPhoneNumber("12345678");
        contactDetails.setPostalCode("123");

        Supplier supplier = new Supplier();
        supplier.setId(1);
        supplier.setCompanyName("Dell");
        supplier.setOrders(Arrays.asList(order));
        supplier.setCountry("USA");
        supplier.setContactDetails(contactDetails);
        supplier.setMinimumOrderQuantity("2");
        supplier.setCreatedDate(new Date());
        supplier.setIsActive(Boolean.TRUE);

        globalSupplier = supplier;
        return supplier;
    }

    //method to update the supplier
    @PutMapping("update")
    public Supplier update(@RequestBody Supplier supplier){
        supplier.setMinimumOrderQuantity("3");
        supplier.setUpdatedDate(new Date());

        globalSupplier = supplier;
        return supplier;
    }

    //method to remove supplier
    @PostMapping("remove/{id}")
    public String remove(@PathVariable Integer id){
        if (globalSupplier != null &&  globalSupplier.getId() == id){
            globalSupplier.setIsActive(Boolean.FALSE);
            System.out.println(globalSupplier.toString());
        }
       return "Supplier removed successfully";
    }
}
