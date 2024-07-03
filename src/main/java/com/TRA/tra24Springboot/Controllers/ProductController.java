package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Services.ProductServices;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServices productServices;
    @Autowired
    SlackService slackService;


    @PostMapping("add")
    public Product addProduct(Product product) {
        return productServices.add(product);
    }

    @PostMapping("delete")
    public <T> ResponseEntity<T> delete(@RequestParam Integer id) throws Exception {
        try {
            String result = productServices.delete(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update")
    public <T> ResponseEntity<T> updateProduct(@RequestParam Integer id, @RequestParam Integer quantity) {
        try {
            String result = productServices.updateProductQuantity(id, quantity);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Updating Failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get")
    public <T> ResponseEntity<T> getProducts() {

        return new ResponseEntity(productServices.getProduct(), HttpStatus.OK);
    }

    @GetMapping("getById")
    public Product getProductByID(@RequestParam Integer productID) {
        return productServices.getProductByID(productID);
    }

    @GetMapping("getByName")
    public List<Product> getProductByName(@RequestParam String productName) {
        return productServices.getProductByName(productName);
    }

    @GetMapping("getByCountry")
    public List<Product> getProductByCountryOfOrigin(@RequestParam String country) {
        return productServices.getProductByCountryOfOrigin(country);
    }

    @GetMapping("getBySize")
    public List<Product> getProductBySize(@RequestParam String size) {
        return productServices.getProductBySize(size);
    }

    @GetMapping("getByColor")
    public List<Product> getProductByColor(@RequestParam String color) {
        return productServices.getProductByColor(color);
    }

    @GetMapping("getBySKU")
    public Product getProductByID(@RequestParam UUID sku) {
        return productServices.getProductBySKU(sku);
    }

    @GetMapping("getByCategory")
    public List<Product> getProductByCategory(@RequestParam String category) {
        return productServices.getProductByCategory(category);
    }

    @GetMapping("getByPrice")
    public List<Product> getProductByPrice(@RequestParam Double price) {
        return productServices.getProductByPrice(price);
    }

    @GetMapping("getByAvailability")
    public List<Product> getProductByAvailability(@RequestParam Boolean isActive) {
        return productServices.getProductByAvailability(isActive);
    }

    @GetMapping("getByQuantity")
    public List<Product> getProductBuQuantity(@RequestParam Integer quantity) {
        return productServices.getProductByQuantity(quantity);
    }

    @Scheduled(cron = "0 9/6 * * *")
    @GetMapping("lowStock")
    public List<Product> lowStockCheck() {
        List<Product> lowStockProducts = productServices.getLowStockProducts();
        if (!lowStockProducts.isEmpty()) {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("-------------------------\nLow stock alert:\n-------------------------\n");
            for (Product product : lowStockProducts) {
                messageBuilder.append("Product ID: ").append(product.getId())
                        .append(" | Product Name: ").append(product.getProductDetails().getName())
                        .append(" | Quantity: ").append(product.getQuantity())
                        .append("\n------------------------------------------------------------\n");
            }
            slackService.sendMessage("afnan", messageBuilder.toString());
        }
        return lowStockProducts;
    }
}
