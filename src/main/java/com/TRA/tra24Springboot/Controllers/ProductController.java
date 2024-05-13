package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

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

}
