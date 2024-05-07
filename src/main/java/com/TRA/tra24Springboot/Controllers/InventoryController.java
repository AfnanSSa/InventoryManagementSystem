package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    public Inventory inventory = new Inventory(); //instance of Inventory Class
    //method for receiving new stock
    @PostMapping("receive")
    public Inventory receiveNewStock(){
        Inventory newInventory = new Inventory();

        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(1);
        productDetails.setName("Laptop");
        productDetails.setColor("Black");
        productDetails.setPrice(350d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setCreatedDate(new Date());

        Product product = new Product();
        product.setProductDetails(productDetails);
        product.setId(1);
        product.setSku(UUID.randomUUID());
        product.setQuantity(100);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());


        newInventory.setProducts(Arrays.asList(product));
        newInventory.setId(1);
        newInventory.setLocation("Sohar");
        newInventory.setAdmin("Afnan");
        newInventory.setPhoneNumber("12345678");
        newInventory.setSupplier("Dell");
        newInventory.setOpeningHours("8 AM");
        newInventory.setClosingHours("8 PM");
        newInventory.setWorkers(Arrays.asList("Jack", "Andrew", "Sam"));
        newInventory.setCreatedDate(new Date());

        inventory = newInventory;
        return newInventory;
    }

}
