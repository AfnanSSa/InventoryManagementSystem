package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

     public Product globalProduct = new Product();

    @PostMapping("add")
    public Product addProduct(){

        Product product = new Product();

        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(1);
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");

        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
        product.setId(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        globalProduct = product;

        return product;
    }

    @PostMapping("delete/{id}")
    public String deleteProduct(@PathVariable Integer id){

            if(globalProduct.getId().equals(id)){
                globalProduct.setIsActive(Boolean.FALSE);
                System.out.println(globalProduct.toString());

        }
        return "Success!";
    }

    @PutMapping("update")
    public Product updateProduct(@RequestBody Product userProduct){


        ProductDetails pd = userProduct.getProductDetails();
        pd.setUpdatedDate(new Date());

        userProduct.setProductDetails(pd);
        userProduct.setUpdatedDate(new Date());

        globalProduct = userProduct;
        return globalProduct;
    }
    @GetMapping("get")
    public  Product reportProduct(){
        return  globalProduct;
    }
}
