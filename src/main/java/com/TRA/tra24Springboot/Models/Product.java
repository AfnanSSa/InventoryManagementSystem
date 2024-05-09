package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Product extends BaseEntity {

    Integer quantity;
    String category;
    UUID sku;

    @OneToOne
    ProductDetails productDetails;

}
