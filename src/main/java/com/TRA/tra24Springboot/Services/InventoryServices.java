package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryServices {

    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    InventoryDTO inventoryDTO;

    //method for receiving new stock
    public Inventory receiveNewStock(Inventory inventory){

        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Laptop");
        productDetails.setColor("Black");
        productDetails.setPrice(350d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setCreatedDate(new Date());
        productDetails = productDetailsRepository.save(productDetails);

        Product product = new Product();
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setQuantity(100);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        product = productRepository.save(product);

        Order order = new Order();
        order.setProductsOrdered(Arrays.asList(product)); //setting the products lists
        order.setCategoryName("Electronics");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());
        order = orderRepository.save(order);

        Supplier supplier = new Supplier();
        supplier.setCompanyName("Dell");
        supplier.setOrders(Arrays.asList(order));
        supplier.setCountry("USA");
        //supplier.setContactDetails(contactDetails);
        supplier.setMinimumOrderQuantity(2);
        supplier.setCreatedDate(new Date());
        supplier.setIsActive(Boolean.TRUE);
        supplier = supplierRepository.save(supplier);

        inventory.setProducts(Arrays.asList(product));
        inventory.setLocation("Sohar");
        inventory.setAdmin("Afnan");
        inventory.setPhoneNumber("12345678");
        inventory.setSupplier(Arrays.asList(supplier));
        inventory.setOpeningHours("8 AM");
        inventory.setClosingHours("8 PM");
        inventory.setWorkers(Arrays.asList("Jack", "Andrew", "Sam"));
        inventory.setCreatedDate(new Date());
        inventory.setIsActive(Boolean.TRUE);

        return inventoryRepository.save(inventory);
    }

    //method to returns
    public Inventory returns(Integer id) {
        Inventory inventory = inventoryRepository.getById(id);

        return inventory;
    }

    //method of write-offs
    public Inventory writeOff(Integer id){
        Inventory inventory = inventoryRepository.getById(id);
        inventory.setIsActive(Boolean.FALSE);
        inventory.setUpdatedDate(new Date());

        return inventoryRepository.save(inventory);
    }

    //method for reporting inventory
    public List<InventoryDTO> getInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventoryDTO.convertToDTOList(inventories);
    }

    public Inventory getInventoryById(Integer inventoryID){
        return inventoryRepository.getInventoryById(inventoryID);
    }

    public List<Inventory> getInventoryByAvailability(Boolean isActive){
        return inventoryRepository.getInventoryByAvailability(isActive);
    }

}
