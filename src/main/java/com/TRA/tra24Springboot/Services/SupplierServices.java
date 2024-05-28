package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

;

@Service
public class SupplierServices {
    @Autowired
    SupplierRepository supplierRepository; //reference to Supplier repo
    @Autowired
    ProductRepository productRepository; //reference to Product repo

    @Autowired
    ProductDetailsRepository productDetailsRepository; //reference to Product Details repo
    @Autowired
    OrderRepository orderRepository; //reference to Order repo

    SupplierDTO supplierDTO;

    public Supplier add(Supplier supplier) {

        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setCreatedDate(new Date());
        productDetails = productDetailsRepository.save(productDetails);

        Product product = new Product();
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
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

        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setPhoneNumber("12345678");
        contactDetails.setPostalCode("123");

        supplier.setCompanyName("Apple");
        supplier.setOrders(Arrays.asList(order));
        supplier.setCountry("USA");
        supplier.setMinimumOrderQuantity(5);
        supplier.setCreatedDate(new Date());
        supplier.setIsActive(Boolean.TRUE);
        supplier.setPaymentMethods(PaymentType.BANK_TRANSFER);
        supplier.setShippingMethods("Air");

        return supplierRepository.save(supplier);
    }

    public String updateMinimumOrderQuantity(Integer id, Integer quantity){
        Supplier supplier = supplierRepository.getById(id);
        supplier.setMinimumOrderQuantity(quantity);
        supplier.setUpdatedDate(new Date());

        supplierRepository.save(supplier);
        return "updated successfully";
    }

    public String remove(Integer id){
        Supplier supplier = supplierRepository.getById(id);
        supplier.setIsActive(Boolean.FALSE);
        System.out.println(supplier.toString());
        supplierRepository.save(supplier);
        return "Removed Successfully";
    }

    public List<SupplierDTO> getSuppliers(){
        List<Supplier> suppliers = supplierRepository.findAll();
        return supplierDTO.convertToDTOList(suppliers);
    }

    public Supplier getSupplierById(Integer supplierId){
        return supplierRepository.getSupplierById(supplierId);
    }

    public List<Supplier> getSupplierByCompanyName(String companyName){
        return supplierRepository.getSupplierByCompanyName(companyName);
    }

    public List<Supplier> getSupplierByCountry(String country){
        return supplierRepository.getSupplierByCountry(country);
    }

    public List<Supplier> getSupplierByMinimumQty(Integer minimumOrderQuantity){
        return supplierRepository.getSupplierByMinimumOrderQty(minimumOrderQuantity);
    }

    public List<Supplier> getSupplierByShippingMethod(String shippingMethods){
        return supplierRepository.getSupplierByShippingMethod(shippingMethods);
    }

    public List<Supplier> getSupplierByPaymentMethod(PaymentType paymentMethods){
        return supplierRepository.getSupplierByPaymentMethod(paymentMethods);
    }
}
