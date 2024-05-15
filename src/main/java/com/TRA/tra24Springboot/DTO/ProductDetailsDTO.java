package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.ProductDetails;
import lombok.Data;

@Data
public class ProductDetailsDTO {
    Integer productID;
    String productName;
    Double price;

    public static ProductDetailsDTO convertToDTOList(ProductDetails productDetails) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        productDetailsDTO.setProductID(productDetails.getId());
        productDetailsDTO.setProductName(productDetails.getName());
        productDetailsDTO.setPrice(productDetails.getPrice());

        return productDetailsDTO;
    }

}
