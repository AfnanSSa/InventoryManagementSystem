package com.TRA.tra24Springboot.Models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Supplier extends BaseEntity{

    String companyName;
    String country;
    ContactDetails contactDetails;
    List<Product> productsOffered;
    Date nextDeliveryTime;
    List<Product> expectedProducts;
    String complaints;
    PaymentType paymentMethods;
    String shippingMethods;
    String minimumOrderQuantity;
    List<Order> orders;

}
