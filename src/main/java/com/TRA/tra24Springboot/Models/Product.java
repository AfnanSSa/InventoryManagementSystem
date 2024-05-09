package com.TRA.tra24Springboot.Models;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
public class Product extends BaseEntity {

    Integer quantity;
    String category;
    UUID sku;
    ProductDetails productDetails;

}
