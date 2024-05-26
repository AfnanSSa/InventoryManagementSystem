package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServices productServices;

    @PostMapping("add")
    public Product addProduct(Product product) {
        return productServices.add(product);
    }

    @PostMapping("delete")
    public String delete(@RequestParam Integer id) {
       return productServices.delete(id);
    }

    @PutMapping("update")
    public String updateProduct(@RequestParam Integer id, @RequestParam Integer quantity) {
        return productServices.updateProductQuantity(id, quantity);
    }

    @GetMapping("get")
    public List<ProductDTO> getProducts(){

        return productServices.getProduct();
    }

    @GetMapping("getById")
    public Product getProductByID(@RequestParam Integer productID){
        return productServices.getProductByID(productID);
    }

    @GetMapping("getByName")
    public List<Product> getProductByName(@RequestParam String productName){
        return productServices.getProductByName(productName);
    }

    @GetMapping("getByCountry")
    public List<Product> getProductByCountryOfOrigin(@RequestParam String country){
        return productServices.getProductByCountryOfOrigin(country);
    }

}
