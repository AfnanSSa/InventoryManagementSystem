package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository; //reference to Product repo
    @Autowired
    ProductDetailsRepository productDetailsRepository; //reference to Product Details repo
    ProductDTO productDTO;

    public Product add(Product product) {

        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails = productDetailsRepository.save(productDetails);

        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);

        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        return productRepository.save(product);
    }

    public String delete(Integer id) {
        Product product = productRepository.getById(id);
        product.setIsActive(Boolean.FALSE);
        System.out.println(product.toString());
        productRepository.save(product);

        return "Deleted Successfully";
    }

    public String updateProductQuantity(Integer id, Integer quantity) {
        Product product = productRepository.getById(id);
        product.setQuantity(quantity);
        product.setUpdatedDate(new Date());

        productRepository.save(product);
        return "Updated Successfully";
    }

   public List<ProductDTO> getProduct() {
        List<Product> products = productRepository.findAll();
        return productDTO.convertToDTOList(products);
    }

   public List<Product> getProductByName(String productName){
        return productRepository.getProductByName(productName);
   }

}
