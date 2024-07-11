package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    Integer quantity;
    String category;
    UUID sku;

    @OneToOne
    ProductDetails productDetails;

}
