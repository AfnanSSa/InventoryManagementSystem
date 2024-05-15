package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {
    ProductDetailsDTO productDetailsDTO;

    //method to convert to DTO
    public static ProductDTO convertToTDO (Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductDetailsDTO(ProductDetailsDTO.convertToDTOList(product.getProductDetails()));
        return productDTO;
    }

    public static List<ProductDTO> convertToDTOList(List<Product> products){
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products){
            productDTOS.add(convertToTDO(product));
        }
        return productDTOS;
    }

}
